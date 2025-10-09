package kr.hhplus.be.server.domain.point.service;

import kr.hhplus.be.server.domain.point.entity.Point;
import org.springframework.stereotype.Service;

@Service
public interface PointService {

    Point getPoint(Long userId);

    Point savePoint(Long userId, Long amount);
}
