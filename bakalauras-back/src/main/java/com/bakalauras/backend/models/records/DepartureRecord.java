package com.bakalauras.backend.models.records;

import com.bakalauras.backend.models.Catch;
import com.bakalauras.backend.models.Gear;
import com.bakalauras.backend.models.lists.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departure_record")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartureRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @Column(name = "logbook_identifier", nullable = false, unique = true)
    private String logbookIdentifier;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "coordinates")
    private String coordinates;

    @ManyToOne
    @JoinColumn(name = "port_id")
    private Port port;

    @ManyToOne
    @JoinColumn(name = "departure_reason_id", nullable = false)
    private DepartureReason departureReason;

    @ManyToOne
    @JoinColumn(name = "fishery_type_id")
    private FisheryType fisheryType;

    @ManyToOne
    @JoinColumn(name = "target_species_group_id")
    private TargetSpeciesGroup targetSpeciesGroup;


    @OneToMany(orphanRemoval = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "departure_record_id")
    @Builder.Default
    private List<Catch> catchOnBoard = new ArrayList<>();

    @OneToMany(orphanRemoval = true, fetch=FetchType.LAZY)
    @JoinColumn(name = "departure_record_id")
    @Builder.Default
    private List<Gear> gearOnBoard = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DepartureRecord that = (DepartureRecord) o;

        return new EqualsBuilder()
                .append(identifier, that.identifier)
                .append(logbookIdentifier, that.logbookIdentifier)
                .append(dateTime, that.dateTime)
                .append(coordinates, that.coordinates)
                .append(port, that.port)
                .append(departureReason, that.departureReason)
                .append(fisheryType, that.fisheryType)
                .append(targetSpeciesGroup, that.targetSpeciesGroup)
                .append(gearOnBoard, that.gearOnBoard)
                .append(catchOnBoard, that.catchOnBoard)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(identifier).append(logbookIdentifier).append(dateTime).append(coordinates).append(port).append(departureReason).append(fisheryType).append(targetSpeciesGroup).append(gearOnBoard).append(catchOnBoard).toHashCode();
    }
}