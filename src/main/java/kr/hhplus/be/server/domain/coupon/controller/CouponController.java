package kr.hhplus.be.server.domain.coupon.controller;

import kr.hhplus.be.server.domain.coupon.dto.request.CouponReq;
import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import kr.hhplus.be.server.domain.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    // 쿠폰 발급
    @PutMapping("")
    public CouponHistory putCoupon(@RequestBody CouponReq couponReq) {

        return couponService.putCoupon(couponReq);
    }
}