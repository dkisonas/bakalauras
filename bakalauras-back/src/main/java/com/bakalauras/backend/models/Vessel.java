package com.bakalauras.backend.models;

import com.bakalauras.backend.models.lists.Nation;
import com.bakalauras.backend.models.users.VesselMaster;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vessel")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vessel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "vessel_master_identifier", referencedColumnName = "identifier")
    private VesselMaster vesselMaster;

    @OneToOne(optional = false)
    @JoinColumn(name = "nation_code", referencedColumnName = "code", nullable = false)
    private Nation nation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Vessel vessel = (Vessel) o;

        return new EqualsBuilder().append(identifier, vessel.identifier).append(name, vessel.name).append(vesselMaster, vessel.vesselMaster).append(nation, vessel.nation).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(identifier).append(name).append(vesselMaster).append(nation).toHashCode();
    }
}