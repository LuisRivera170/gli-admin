package com.applab.gli.utils;

import com.applab.gli.dto.ResponseDTO;
import com.applab.gli.enumeration.ResponseStatus;

import java.util.Map;

import static java.time.LocalDateTime.now;

public class Utils {

    public static ResponseDTO buildResponseDTO(String message, ResponseStatus status) {
        return ResponseDTO.builder()
                .timeStamp(now())
                .status(status)
                .message(message)
                .build();
    }

    public static ResponseDTO buildResponseDTO(String message, ResponseStatus status, Map<?,?> data) {
        return ResponseDTO.builder()
            .timeStamp(now())
            .status(status)
            .message(message)
            .data(data)
            .build();
    }

    /* Validaciones */

    /**
     * Verifica si una cadena es null o contiene la palabra null o está vacía.
     *
     * @param value Cadena a verificar.
     *
     * @return true si la cadena es nula, está vacía o es igual a la palablra "null".
    **/
    public static boolean isNullOrBlank (String value) {
        return null == value || value.isEmpty() || "null".equalsIgnoreCase(value.trim());
    }

}
