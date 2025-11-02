package kr.hhplus.be.server.common.custom;

public class ProductExceptionHandler {

    // 상품을 찾을 수 없는 경우
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    // 잔액이 부족한 경우
    public static class PointBalanceNotEnoughException extends RuntimeException {
        public PointBalanceNotEnoughException(String message) {
            super(message);
        }
    }

    // 충전 중 오류
    public static class PointChargeException extends RuntimeException {
        public PointChargeException(String message) {
            super(message);
        }
    }


}
