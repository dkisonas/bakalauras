package com.bakalauras.backend.models.records;

import com.bakalauras.backend.models.Catch;
import com.bakalauras.backend.models.CatchOperationType;
import com.bakalauras.backend.models.Logbook;
import com.bakalauras.backend.models.lists.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catch_record")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatchRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @Column(name = "logbook_identifier", nullable = false, unique = true)
    private String logbookIdentifier;

    @Enumerated(EnumType.STRING)
    private CatchOperationType operationType;

    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "coordinates", nullable = false)
    private String coordinates;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fao_area_id",  nullable = false)
    private FaoArea faoArea;

    @ManyToOne
    @JoinColumn(name = "rfmo_id", nullable = false)
    private Rfmo rfmo;

    @ManyToOne
    @JoinColumn(name = "vessel_activity_id", nullable = false)
    private VesselActivity vesselActivity;

    @Column(name = "operation_count", nullable = false)
    private Integer operationCount;

    @ManyToOne
    @JoinColumn(name = "fishery_type_id", nullable = false)
    private FisheryType fisheryType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_species_group_id", nullable = false)
    private TargetSpeciesGroup targetSpeciesGroup;

    @OneToMany
    @Builder.Default
    private List<Catch> catches = new ArrayList<>();

}