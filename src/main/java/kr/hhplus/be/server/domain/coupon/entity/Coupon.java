package kr.hhplus.be.server.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    private Long orderId;

    private Long discountRate;

    private Long allCnt;

    private Long allocatedCnt;

    private String useYn;

    private Long updateMillis;

}