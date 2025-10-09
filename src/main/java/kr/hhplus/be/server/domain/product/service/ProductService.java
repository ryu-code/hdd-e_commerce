package kr.hhplus.be.server.domain.product.service;

import kr.hhplus.be.server.domain.point.entity.Point;
import kr.hhplus.be.server.domain.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Product getProduct(Long productId);
}
