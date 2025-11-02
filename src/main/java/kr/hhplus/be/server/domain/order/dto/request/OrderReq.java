package kr.hhplus.be.server.domain.order.dto.request;

public record OrderReq (
        Long userId,
        Long productId,
        Long point,
        Long orderCnt
) {}
