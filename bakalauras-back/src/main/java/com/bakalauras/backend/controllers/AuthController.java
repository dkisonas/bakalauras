package com.bakalauras.backend.controllers;

import com.bakalauras.backend.payload.request.LoginRequest;
import com.bakalauras.backend.payload.request.SignupRequest;
import com.bakalauras.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService service;

	public AuthController(AuthService service) {
		this.service = service;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		try {
			return ResponseEntity.ok(service.loginUser(loginRequest));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		try {
			return ResponseEntity.ok(service.registerUser(signUpRequest));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}


}
