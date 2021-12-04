package com.applab.gli.domain;

import com.applab.gli.enumeration.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "areas")
public class Area {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String description;

    private Status status;

}
