package com.applab.gli.dto;

import com.applab.gli.enumeration.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class ResponseDTO {

    protected LocalDateTime timeStamp;
    protected ResponseStatus status;
    protected String message;
    protected String developerMessage;
    protected Map<?, ?> data;

}
