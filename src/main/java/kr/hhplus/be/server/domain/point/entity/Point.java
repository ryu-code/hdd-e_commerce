package kr.hhplus.be.server.domain.point.entity;

import lombok.Data;

@Data
public class Point {

    private Long userId;

    private Long point;

    private Long updateMillis;

}