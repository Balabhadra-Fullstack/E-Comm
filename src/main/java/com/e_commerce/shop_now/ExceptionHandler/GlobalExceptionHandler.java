package com.e_commerce.shop_now.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
     @ExceptionHandler(CustomExe.class)
    public ResponseEntity<?> handleCustomException(CustomExe ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
    @ExceptionHandler(UserNotFound.class)
public ResponseEntity<?> handleUserNotFoundException(UserNotFound ex) {
    return ResponseEntity.status(404).body(ex.getMessage());
}
}
