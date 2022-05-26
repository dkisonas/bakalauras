package com.bakalauras.backend.models;

import com.bakalauras.backend.models.lists.GearType;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "gear")
public class Gear {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gear_type_id", nullable = false)
    private GearType gearType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Gear gear = (Gear) o;

        return new EqualsBuilder().append(identifier, gear.identifier).append(gearType, gear.gearType).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(identifier).append(gearType).toHashCode();
    }
}