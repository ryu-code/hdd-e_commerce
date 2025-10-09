package kr.hhplus.be.server.domain.order.controller;

import kr.hhplus.be.server.domain.order.dto.request.OrderReq;
import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class OrderController {

    private final OrderService orderService;

    // 주문
    @PutMapping("")
    public Order putOrder(@RequestBody OrderReq orderReq) {

        return orderService.putOrder(orderReq);
    }
}