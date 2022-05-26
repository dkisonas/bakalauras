package com.bakalauras.backend.exception;

import com.bakalauras.backend.payload.response.ErrorMessages;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super(String.format(ErrorMessages.USERNAME_TAKEN, username));
    }
}
