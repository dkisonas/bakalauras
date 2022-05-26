package com.bakalauras.backend.exception;

import com.bakalauras.backend.payload.response.ErrorMessages;

public class VesselMasterNotFound extends RuntimeException {
    public VesselMasterNotFound(String identifier) {
        super(String.format(ErrorMessages.VESSEL_MASTER_NOT_FOUND, identifier));
    }
}
