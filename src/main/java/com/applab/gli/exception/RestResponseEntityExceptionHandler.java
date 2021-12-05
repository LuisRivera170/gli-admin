package com.applab.gli.exception;

import com.applab.gli.dto.ResponseDTO;
import com.applab.gli.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Controller
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UploadAdminPhotoException.class)
    public ResponseEntity<ResponseDTO> uploadAdminPhotoException() {
        return ResponseEntity
            .internalServerError()
            .body(
                Utils.buildErrorResponseDTO("error")
            );
    }

}
