package kr.hhplus.be.server.domain.point.entity;

import lombok.Data;

@Data
public class PointHistory {

    private Long userId;

    private Long point;

    private Long amount;

    private String chargeType; // CHARGE, USE, CANCEL

    private Long updateMillis;

}