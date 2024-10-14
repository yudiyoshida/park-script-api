package com.example.parkscript.shared.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public AppException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public AppExceptionBody getErrorBody() {
        return new AppExceptionBody(this.status, this.message);
    }
}
