package com.applab.gli.domain;

import com.applab.gli.enumeration.Status;
import com.applab.gli.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "admins")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    // TODO: Photo

    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(
        name = "area_id",
        referencedColumnName = "id"
    )
    private Area area;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Transient
    private String fullName;

    public String getFullName() {
        return this.name.concat(!Utils.isNullOrBlank(this.lastName) ? " ".concat(this.lastName) : "");
    }

    public Admin(String name, String lastName, String email, Status status, Area area, LocalDateTime createdAt) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.area = area;
        this.createdAt = createdAt;
    }

}
