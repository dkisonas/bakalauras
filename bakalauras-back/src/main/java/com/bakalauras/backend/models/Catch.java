package com.bakalauras.backend.models;

import com.bakalauras.backend.models.lists.GearType;
import com.bakalauras.backend.models.lists.Presentation;
import com.bakalauras.backend.models.lists.Preservation;
import com.bakalauras.backend.models.lists.Species;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "catch")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @OneToOne
    @JoinColumn(name = "gear_type_id")
    private GearType gear;

    @ManyToOne(optional = false)
    @JoinColumn(name = "species_id", nullable = false)
    private Species species;

    @Column(name = "count")
    private Integer count;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "presentation_id", nullable = false)
    private Presentation presentation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "preservation_id", nullable = false)
    private Preservation preservation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Catch aCatch = (Catch) o;

        return new EqualsBuilder().append(identifier, aCatch.identifier).append(gear, aCatch.gear).append(species, aCatch.species).append(count, aCatch.count).append(weight, aCatch.weight).append(presentation, aCatch.presentation).append(preservation, aCatch.preservation).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(identifier).append(gear).append(species).append(count).append(weight).append(presentation).append(preservation).toHashCode();
    }
}