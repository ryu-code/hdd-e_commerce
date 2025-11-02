package kr.hhplus.be.server.domain.coupon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kr.hhplus.be.server.domain.coupon.dto.request.CouponReq;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import kr.hhplus.be.server.domain.coupon.service.CouponService;
import kr.hhplus.be.server.domain.point.entity.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @Operation(
            summary = "사용자에게 쿠폰 발급",
            description = "선착순으로 당첨된 사용자에게 쿠폰을 발급합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "쿠폰 발급 성공",
                    content = @Content(schema = @Schema(implementation = Coupon.class))
            )
    })
    @PutMapping("")
    public CouponHistory putCoupon(@RequestBody CouponReq couponReq) {

        return couponService.putCoupon(couponReq);
    }
}