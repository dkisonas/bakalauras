package com.bakalauras.backend.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private String role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    private String vesselIdentifier;

    private String badgeNumber;

}
