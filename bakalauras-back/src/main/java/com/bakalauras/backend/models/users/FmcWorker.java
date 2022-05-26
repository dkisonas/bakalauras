package com.bakalauras.backend.models.users;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "fmc_worker")
@NoArgsConstructor
public class FmcWorker extends User implements Serializable {
    @Column(name = "worker_number", nullable = false, unique = true)
    private String workerNumber;

    @Builder
    public FmcWorker(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 120) String name, @NotBlank @Size(max = 120) String lastName, Role role, String workerNumber) {
        super(id, username, password, name, lastName, role);
        this.workerNumber = workerNumber;
    }

    public String getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(String workerNumber) {
        this.workerNumber = workerNumber;
    }
}