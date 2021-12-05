package com.applab.gli.dto;

import com.applab.gli.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class PagingAdminsDTO extends PagingDTO {

    private String name;

    private Long areaId;

    private Status status;

}
