package com.event.eventmanager.exeptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleCommonException(Exception exception) {
        log.error("Handle common exception", exception);
        var messageResponse = new ErrorMessageResponse(
                "Internal Server Error",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(500)
                .body(messageResponse);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception exception) {
        log.error("Handle not found exception", exception);
        var messageResponse = new ErrorMessageResponse(
                "Entity not found",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(404)
                .body(messageResponse);
    }

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<Object> handleIllegalArgumentException(Exception exception) {
        log.error("Handle illegal argument exception", exception);
        var messageResponse = new ErrorMessageResponse(
                "Incorrect request",
                exception.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(400)
                .body(messageResponse);
    }
}
