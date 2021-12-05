package com.applab.gli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class AdminDTO {

    private Long adminId;
    private String adminName;
    private String adminLastName;
    private String adminFullName;
    private String adminEmail;
    private String adminStatus;
    private AreaDTO adminArea;
    private LocalDateTime adminCreatedAt;
    private LocalDateTime adminUpdatedAt;
    private LocalDateTime adminDeletedAt;

}
