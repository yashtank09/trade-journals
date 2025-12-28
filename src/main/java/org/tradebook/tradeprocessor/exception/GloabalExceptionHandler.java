package org.tradebook.tradeprocessor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tradebook.tradeprocessor.dto.response.DataApiResponse;

@ControllerAdvice
public class GloabalExceptionHandler {
    @ExceptionHandler(FileValidationException.class)
    public ResponseEntity<DataApiResponse<Void>> handleFileValidationException(FileValidationException ex) {
        return ResponseEntity
                .badRequest()
                .body(new DataApiResponse<>("error", 400, ex.getMessage()));
    }

    @ExceptionHandler(FileStorageExcpetion.class)
    public ResponseEntity<DataApiResponse<Void>> handleFileStorageException(FileStorageExcpetion ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new DataApiResponse<>("error", 500, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataApiResponse<Void>> handleGlobalException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new DataApiResponse<>("error", 500, "An unexpected error occurred {} " + ex.getMessage()));
    }
}
