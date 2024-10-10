package com.example.parkscript.shared.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppExceptionBody> handleRuntimeException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getErrorBody());
    }
}
