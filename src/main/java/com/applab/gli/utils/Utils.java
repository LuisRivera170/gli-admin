package com.applab.gli.utils;

import com.applab.gli.dto.ResponseDTO;
import com.applab.gli.enumeration.ResponseStatus;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

import static com.applab.gli.utils.Constants.secretWord;
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

    public static ResponseDTO buildErrorResponseDTO(String developerMessage) {
        return ResponseDTO.builder()
            .timeStamp(now())
            .developerMessage(developerMessage)
            .build();
    }

    /** JWT **/

    public static Algorithm algorithm;

    public static Algorithm getAlgorithm() {
        if (algorithm == null) algorithm = Algorithm.HMAC256(secretWord.getBytes());
        return algorithm;
    }

    public static DecodedJWT decodedJWT(String token) {
        JWTVerifier verifier = JWT.require(getAlgorithm()).build();
        return verifier.verify(token);
    }

    /** Validaciones **/

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
