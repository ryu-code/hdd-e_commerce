package kr.hhplus.be.server.domain.order.service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.common.custom.OrderExceptionHandler;
import kr.hhplus.be.server.common.custom.ProductExceptionHandler;
import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import kr.hhplus.be.server.domain.coupon.repository.CouponHistoryRepository;
import kr.hhplus.be.server.domain.order.dto.request.OrderReq;
import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.entity.OrderHistory;
import kr.hhplus.be.server.domain.order.repository.OrderHistoryRepository;
import kr.hhplus.be.server.domain.order.repository.OrderRepository;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final CouponHistoryRepository couponHistoryRepository;

    @Transactional
    public Order putOrder(OrderReq orderReq) {
        try {
            Order order = new Order();
            order.setProductId(orderReq.productId());
            order.setUserId(orderReq.userId());
            order.setProductCnt(orderReq.orderCnt());

            Product product = productRepository.findByProductId(orderReq.productId());
            order.setPoint(product.getPoint());

            if (product.getCount() < orderReq.orderCnt()) {
                throw new OrderExceptionHandler.OrderCntNotEnoughException("상품 수가 충분하지 않습니다.");
            }

            CouponHistory couponHistory = new CouponHistory();
            List<CouponHistory> couponHistories = couponHistoryRepository.findByUserIdOrderByDiscountRate(orderReq.userId());

            if (couponHistories != null) {

                // 구매
                order.setPoint(orderReq.point() * (100 - couponHistories.get(0).getDiscountRate()) / 100);
                Order putOrder = orderRepository.save(order);

                if (putOrder != null) {
                    // 상품 남은 수
                    product.setCount(product.getCount() - order.getProductCnt());
                    productRepository.save(product);

                    // 주문 히스토리
                    OrderHistory orderHistory = new OrderHistory();
                    orderHistory.setProductId(putOrder.getProductId());
                    orderHistory.setUserId(putOrder.getUserId());
                    orderHistory.setPoint(putOrder.getPoint());
                    orderHistory.setProductCnt(putOrder.getProductCnt());

                    orderHistoryRepository.save(orderHistory);

                    // 쿠폰 use_yn = Y로 변경
                    couponHistory.setUseYn("Y");
                    couponHistory.setOrderId(putOrder.getId());

                    couponHistoryRepository.save(couponHistory);
                }

                return putOrder;
            }

            return order;

        } catch (Exception e) {
            throw new RuntimeException("상품 조회 중 오류가 발생했습니다.", e);
        }
    }
}
