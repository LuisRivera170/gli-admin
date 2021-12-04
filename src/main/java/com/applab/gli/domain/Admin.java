package com.applab.gli.domain;

import com.applab.gli.enumeration.Status;
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
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    // TODO: Photo

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

}
