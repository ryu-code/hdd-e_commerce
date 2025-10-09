package kr.hhplus.be.server.domain.point.controller;


import kr.hhplus.be.server.domain.point.dto.request.PointChargeRequest;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    // 잔액 조회
    @GetMapping("/{userId}")
    public Point getPoint(@PathVariable Long userId) {

        return pointService.getPoint(userId);
    }

    // 잔액 충전
    @PostMapping("/charge")
    public Point chargePoints(@RequestBody PointChargeRequest pointChargeRequest) {

        return pointService.savePoint(pointChargeRequest.userId(), pointChargeRequest.amount());
    }
}