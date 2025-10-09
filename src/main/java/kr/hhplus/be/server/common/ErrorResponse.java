package kr.hhplus.be.server.common;

import kr.hhplus.be.server.common.custom.PointExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorResponse {

    @ExceptionHandler(PointExceptionHandler.PointNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePointNotFound(PointExceptionHandler.PointNotFoundException ex) {
        Map<String, String> body = Map.of(
                "error", "POINT_NOT_FOUND",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
