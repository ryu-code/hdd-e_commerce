package kr.hhplus.be.server.domain.point.service;

import kr.hhplus.be.server.common.custom.PointExceptionHandler;
import kr.hhplus.be.server.domain.point.entity.PointHistory;
import kr.hhplus.be.server.domain.point.repository.PointHistoryRepository;
import kr.hhplus.be.server.domain.point.repository.PointRepository;
import kr.hhplus.be.server.domain.point.entity.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Override
    public Point getPoint(Long userId) {
        try {
            Point point = pointRepository.findByUserId(userId);
            if (point == null) {
                throw new PointExceptionHandler.PointNotFoundException("포인트를 찾을 수 없습니다.");
            }
            return point;
        } catch (Exception e) {
            throw new RuntimeException("포인트 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public Point savePoint(Long userId, Long amount) {

        try {
            // 충전 양 < 0 이면 에러
            if (amount <= 0) {
                throw new PointExceptionHandler.PointChargeException("충전 금액은 0보다 커야 합니다.");
            }

            // 포인트 충전
            Point point = pointRepository.findByUserId(userId);
            point.setPoint(point.getPoint() + amount);
            pointRepository.save(point);

            // 포인트 충전 내역 저장
            PointHistory pointHistory = new PointHistory();
            pointHistory.setUserId(userId);
            pointHistory.setAmount(amount);
            pointHistory.setChargeType("CHARGE");
            pointHistory.setUpdateMillis(System.currentTimeMillis());
            pointHistoryRepository.save(pointHistory);

            return point;
        } catch (Exception e) {
            throw new RuntimeException("포인트 충전 중 오류가 발생했습니다.", e);
        }
    }
}
