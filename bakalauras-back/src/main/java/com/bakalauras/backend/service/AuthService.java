package com.bakalauras.backend.service;

import com.bakalauras.backend.exception.RoleNotFoundException;
import com.bakalauras.backend.exception.UsernameAlreadyExistsException;
import com.bakalauras.backend.models.*;
import com.bakalauras.backend.models.users.*;
import com.bakalauras.backend.payload.request.LoginRequest;
import com.bakalauras.backend.payload.request.SignupRequest;
import com.bakalauras.backend.payload.response.JwtResponse;
import com.bakalauras.backend.payload.response.MessageResponse;
import com.bakalauras.backend.repository.RoleRepository;
import com.bakalauras.backend.repository.UserRepository;
import com.bakalauras.backend.repository.VesselRepository;
import com.bakalauras.backend.security.jwt.JwtUtils;
import com.bakalauras.backend.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VesselRepository vesselRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;


    public JwtResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles.get(0));
    }

    public MessageResponse registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameAlreadyExistsException(signUpRequest.getUsername());
        }

        Role userRole = getUserRole(signUpRequest.getRole());

        User user;

        switch (userRole.getName()) {
            case ROLE_VESSEL_MASTER -> {
                Vessel vessel = getVessel(signUpRequest.getVesselIdentifier());
                user = createVesselMaster(signUpRequest, userRole, vessel);
                userRepository.save(user);
                updateVessel(vessel, (VesselMaster) user);

            }
            case ROLE_FMC_WORKER -> user = createFmcWorker(signUpRequest, userRole);
            case ROLE_INSPECTOR -> user = createInspector(signUpRequest, userRole);
            default -> throw new RuntimeException("Invalid role");
        }

        userRepository.save(user);
        return new MessageResponse(String.format("User %s has been registered successfully.", user.getUsername()));
    }

    private Inspector createInspector(SignupRequest signUpRequest, Role userRole) {
        return Inspector.builder()
                .name(signUpRequest.getName())
                .lastName(signUpRequest.getLastName())
                .role(userRole)
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .badgeNumber(signUpRequest.getBadgeNumber())
                .build();
    }

    private FmcWorker createFmcWorker(SignupRequest signUpRequest, Role userRole) {
        return FmcWorker.builder()
                .name(signUpRequest.getName())
                .lastName(signUpRequest.getLastName())
                .role(userRole)
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .workerNumber(String.valueOf(UUID.randomUUID()))
                .build();
    }

    private VesselMaster createVesselMaster(SignupRequest signUpRequest, Role userRole, Vessel vessel) {
        return VesselMaster.builder()
                .identifier(String.valueOf(UUID.randomUUID()))
                .name(signUpRequest.getName())
                .lastName(signUpRequest.getLastName())
                .role(userRole)
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .vessel(vessel)
                .build();
    }

    private void updateVessel(Vessel vessel, VesselMaster vesselMaster) {
        if (vesselMaster != null && vessel != null) {
            vessel.setVesselMaster(vesselMaster);
            vesselRepository.save(vessel);
        }
    }

    private Vessel getVessel(String identifier) {
        if (StringUtils.isNotBlank(identifier)) {
            return vesselRepository.findByIdentifier(identifier)
                    .orElseThrow(() -> new RuntimeException(
                            String.format("Vessel {identifier=%s} not found", identifier)));
        }
        return null;
    }


    private Role getUserRole(String role) {
        if ("vessel_master".equalsIgnoreCase(role)) {
            return fetchRole(ERole.ROLE_VESSEL_MASTER);
        } else if ("fmc_worker".equalsIgnoreCase(role)) {
            return fetchRole(ERole.ROLE_FMC_WORKER);
        } else if ("inspector".equalsIgnoreCase(role)) {
            return fetchRole(ERole.ROLE_INSPECTOR);
        }
        throw new RoleNotFoundException(role);
    }

    private Role fetchRole(ERole eRole) {
        return roleRepository.findByName(eRole)
                .orElseThrow(() -> new RoleNotFoundException(eRole.name()));
    }
}
