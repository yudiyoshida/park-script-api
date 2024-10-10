package com.example.parkscript.shared.errors;

import org.springframework.http.HttpStatus;

public record AppExceptionBody(HttpStatus status, String message) { }
