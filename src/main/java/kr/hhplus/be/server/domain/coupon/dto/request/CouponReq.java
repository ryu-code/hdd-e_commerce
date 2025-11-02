package kr.hhplus.be.server.domain.coupon.dto.request;

public record CouponReq(
        Long userId,
        Long discountRate,
        String useYn,
        Long orderId
) {}
