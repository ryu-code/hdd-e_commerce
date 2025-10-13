package kr.hhplus.be.server.domain.coupon.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.order.entity.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CouponHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_history_id")
    private Long id;

    @Column(nullable = false)
    private Long userId;

    private Long orderId;

    @Column(nullable = false)
    private Long discountRate;

    private String useYn;

    private Long updateMillis;

}