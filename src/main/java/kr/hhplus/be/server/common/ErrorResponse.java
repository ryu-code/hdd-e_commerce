package kr.hhplus.be.server.common;

import kr.hhplus.be.server.common.custom.OrderExceptionHandler;
import kr.hhplus.be.server.common.custom.PointExceptionHandler;
import kr.hhplus.be.server.common.custom.ProductExceptionHandler;
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

    @ExceptionHandler(ProductExceptionHandler.ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(ProductExceptionHandler.ProductNotFoundException ex) {
        Map<String, String> body = Map.of(
                "error", "PRODUCT_NOT_FOUND",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(OrderExceptionHandler.OrderCntNotEnoughException.class)
    public ResponseEntity<Map<String, String>> handleOrderCntNotEnough(OrderExceptionHandler.OrderCntNotEnoughException ex) {
        Map<String, String> body = Map.of(
                "error", "PRODUCT_NOT_FOUND",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
