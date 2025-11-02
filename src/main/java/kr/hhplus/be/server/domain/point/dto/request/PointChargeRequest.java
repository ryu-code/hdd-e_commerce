package kr.hhplus.be.server.domain.point.dto.request;

public record PointChargeRequest (
        Long userId,
        Long amount
) {}