package kr.hhplus.be.server.domain.coupon.service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.common.custom.OrderExceptionHandler;
import kr.hhplus.be.server.domain.coupon.dto.request.CouponReq;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import kr.hhplus.be.server.domain.coupon.repository.CouponHistoryRepository;
import kr.hhplus.be.server.domain.coupon.repository.CouponRepository;
import kr.hhplus.be.server.domain.order.dto.request.OrderReq;
import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.entity.OrderHistory;
import kr.hhplus.be.server.domain.order.repository.OrderHistoryRepository;
import kr.hhplus.be.server.domain.order.repository.OrderRepository;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponHistoryRepository couponHistoryRepository;

    public CouponHistory putCoupon(CouponReq couponReq) {
        try {
            Coupon getCoupon = new Coupon();
            List<Coupon> couponsList = couponRepository.findAllOrderByDiscountRate();

            for (Coupon coupon : couponsList) {
                if (coupon.getAllCnt() - coupon.getAllocatedCnt() > 0) {
                    getCoupon = coupon;
                    break;
                }
            }

            CouponHistory couponHistory = new CouponHistory();
            couponHistory.setUserId(couponReq.userId());
            couponHistory.setDiscountRate(getCoupon.getDiscountRate());
            couponHistory.setOrderId(null);
            couponHistory.setUseYn("N");

            return couponHistoryRepository.save(couponHistory);
        } catch (Exception e) {
            throw new RuntimeException("쿠폰 발급 중 오류가 발생했습니다.", e);
        }
    }
}
