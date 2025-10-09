package kr.hhplus.be.server.common.custom;

public class OrderExceptionHandler {

    // 주문내역을 찾을 수 없는 경우
    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    // 상품수가 부족한 경우
    public static class OrderCntNotEnoughException extends RuntimeException {
        public OrderCntNotEnoughException(String message) {
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
