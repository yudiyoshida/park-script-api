package com.example.parkscript.shared.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<AppExceptionBody> handleAppException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getErrorBody());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AppExceptionBody> handleRequiredRequestBodyException(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new AppExceptionBody(HttpStatus.BAD_REQUEST, "Corpo da requisição é obrigatório"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppExceptionBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .findFirst()
                .map(ObjectError::getDefaultMessage)
                .orElse("Erro de validação");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new AppExceptionBody(HttpStatus.BAD_REQUEST, error));
    }
}
