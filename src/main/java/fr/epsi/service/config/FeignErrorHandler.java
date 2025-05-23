package fr.epsi.service.config;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FeignErrorHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException ex) {
        HttpStatus status = HttpStatus.resolve(ex.status());

        return ResponseEntity
                .status(status)
                .body(ex.contentUTF8());
    }
}