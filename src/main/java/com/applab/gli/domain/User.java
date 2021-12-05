package com.applab.gli.domain;

import com.applab.gli.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "user_name",unique = true)
    private String userName;

    @Column
    private String password;

    private Status status;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "role_id",
            referencedColumnName = "id"
        )
    )
    private List<Role> roles;

    public User(String userName, String password, Status status, List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }
}
