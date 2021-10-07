package ru.gb.springdata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new MarketError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchValidationException(DataValidationException e) {
        return new ResponseEntity<>(new MarketError(e.getMessages()), HttpStatus.BAD_REQUEST);
    }
}
