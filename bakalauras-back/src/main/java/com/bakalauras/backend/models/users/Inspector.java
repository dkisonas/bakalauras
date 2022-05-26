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
@Table(name = "inspector")
@NoArgsConstructor
public class Inspector extends User implements Serializable {
    @Column(name = "badge_number", nullable = false, unique = true)
    private String badgeNumber;

    @Builder
    public Inspector(Long id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, @NotBlank @Size(max = 120) String name, @NotBlank @Size(max = 120) String lastName, Role role, String badgeNumber) {
        super(id, username, password, name, lastName, role);
        this.badgeNumber = badgeNumber;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }
}