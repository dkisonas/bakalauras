package com.bakalauras.backend.models;

import com.bakalauras.backend.models.records.CatchRecord;
import com.bakalauras.backend.models.records.DepartureRecord;
import com.bakalauras.backend.models.records.ReturnToPortRecord;
import com.bakalauras.backend.models.users.VesselMaster;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "logbook")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Logbook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "identifier", nullable = false, unique = true)
    private String identifier;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_changed_at", nullable = false)
    private LocalDateTime lastChangedAt;

    @Column(name = "inspector_comment", length = 1000)
    private String inspectorComment;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "departure_record_id", referencedColumnName = "logbook_identifier")
    private DepartureRecord departureRecord;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "return_to_port_record_id", referencedColumnName = "logbook_identifier")
    private ReturnToPortRecord returnToPortRecord;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vessel_master_id", nullable = false)
    private VesselMaster vesselMaster;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "logbook_identifier", referencedColumnName = "identifier")

    @Builder.Default
    private List<CatchRecord> catchRecords = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Logbook logbook = (Logbook) o;

        return new EqualsBuilder()
                .append(identifier, logbook.identifier)
                .append(createdAt, logbook.createdAt)
                .append(departureRecord, logbook.departureRecord)
                .append(returnToPortRecord, logbook.returnToPortRecord)
                .append(vesselMaster, logbook.vesselMaster)
                .append(catchRecords, logbook.catchRecords)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(identifier).append(createdAt).append(lastChangedAt).append(inspectorComment).append(departureRecord).append(returnToPortRecord).append(vesselMaster).append(catchRecords).toHashCode();
    }
}