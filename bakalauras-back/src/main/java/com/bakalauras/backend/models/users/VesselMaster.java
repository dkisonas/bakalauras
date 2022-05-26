package com.bakalauras.backend.models.users;

import com.bakalauras.backend.models.Logbook;
import com.bakalauras.backend.models.Vessel;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vessel_master")
@Getter
@Setter
@NoArgsConstructor
public class VesselMaster extends User implements Serializable {

    @Builder
    public VesselMaster(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 120) String name, @NotBlank @Size(max = 120) String lastName, Role role, List<Logbook> logbooks, String identifier, Vessel vessel) {
        super(id, username, password, name, lastName, role);
        this.logbooks = new ArrayList<>();
        this.identifier = identifier;
        this.vessel = vessel;
    }

    @OneToMany(mappedBy = "vesselMaster")
    private List<Logbook> logbooks = new ArrayList<>();

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @OneToOne
    @JoinColumn(name = "vessel_identifier", referencedColumnName = "identifier")
    private Vessel vessel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        VesselMaster that = (VesselMaster) o;

        return new EqualsBuilder().append(identifier, that.identifier).append(vessel, that.vessel).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(identifier).append(vessel).toHashCode();
    }
}