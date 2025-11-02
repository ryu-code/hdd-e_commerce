package kr.hhplus.be.server.domain.point.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.domain.point.dto.request.PointChargeRequest;
import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    @Operation(
            summary = "사용자의 포인트 조회",
            description = "사용자 ID로 포인트 정보를 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = Point.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "사용자를 찾을 수 없음"
            )
    })
    @GetMapping("/{userId}")
    public Point getPoint(@PathVariable Long userId) {

        return pointService.getPoint(userId);
    }


    @Operation(
            summary = "사용자의 포인트 충전",
            description = "사용자의 포인트를 충전합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "충전 성공",
                    content = @Content(schema = @Schema(implementation = Point.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "사용자를 찾을 수 없음"
            )
    })
    @PostMapping("/charge")
    public Point chargePoints(@RequestBody PointChargeRequest pointChargeRequest) {

        return pointService.savePoint(pointChargeRequest.userId(), pointChargeRequest.amount());
    }
}