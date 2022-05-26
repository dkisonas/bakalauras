package com.bakalauras.backend.models.records;

import com.bakalauras.backend.models.Logbook;
import com.bakalauras.backend.models.lists.FisheryType;
import com.bakalauras.backend.models.lists.Port;
import com.bakalauras.backend.models.lists.ReturnReason;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "return_to_port_record")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnToPortRecord implements Serializable {
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

    @OneToOne
    @JoinColumn(name = "port_id")
    private Port port;

    @ManyToOne
    @JoinColumn(name = "return_reason_id", nullable = false)
    private ReturnReason returnReason;



}