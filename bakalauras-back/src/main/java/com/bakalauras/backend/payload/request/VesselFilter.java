package com.bakalauras.backend.payload.request;

import lombok.Data;

@Data
public class VesselFilter {

    private String vesselIdentifier;
    private String nationCode;

    public void setEmptyFilters() {
        if (vesselIdentifier == null) {
            vesselIdentifier = "*";
        }

        if (nationCode == null) {
            nationCode = "*";
        }
    }

}
