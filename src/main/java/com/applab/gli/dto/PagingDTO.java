package com.applab.gli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort.Direction;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingDTO {

    private Integer page;

    private Integer pageSize;

    private String sortField;

    private Direction sortDirection;

}
