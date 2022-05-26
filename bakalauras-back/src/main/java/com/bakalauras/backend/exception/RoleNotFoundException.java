package com.bakalauras.backend.exception;

import com.bakalauras.backend.payload.response.ErrorMessages;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String role) {
        super(String.format(ErrorMessages.ROLE_NOT_FOUND, role));
    }
}
