package com.applab.gli.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "areas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Area {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String description;

    public Area(Long id) {
        this.id = id;
    }

    public Area(String description) {
        this.description = description;
    }
}
