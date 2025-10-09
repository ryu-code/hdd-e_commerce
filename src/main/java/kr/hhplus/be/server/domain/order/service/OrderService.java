package kr.hhplus.be.server.domain.order.service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.common.custom.OrderExceptionHandler;
import kr.hhplus.be.server.common.custom.ProductExceptionHandler;
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

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    @Transactional
    public Order putOrder(OrderReq orderReq) {
        try {
            Order order = new Order();
            order.setProductId(orderReq.productId());
            order.setPoint(orderReq.point());
            order.setUserId(orderReq.userId());
            order.setProductCnt(orderReq.orderCnt());

            Product product = productRepository.findByProductId(orderReq.productId());

            if (product.getCount() < orderReq.orderCnt()) {
                throw new OrderExceptionHandler.OrderCntNotEnoughException("상품 수가 충분하지 않습니다.");
            }

            Order putOrder = orderRepository.save(order);

            if (putOrder != null) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setProductId(putOrder.getProductId());
                orderHistory.setUserId(putOrder.getUserId());
                orderHistory.setPoint(putOrder.getPoint());
                orderHistory.setProductCnt(putOrder.getProductCnt());

                orderHistoryRepository.save(orderHistory);
            }

            return putOrder;
        } catch (Exception e) {
            throw new RuntimeException("상품 조회 중 오류가 발생했습니다.", e);
        }
    }
}
