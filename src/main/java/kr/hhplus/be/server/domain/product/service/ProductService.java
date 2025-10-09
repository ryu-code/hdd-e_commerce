package kr.hhplus.be.server.domain.product.service;

import kr.hhplus.be.server.common.custom.ProductExceptionHandler;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(Long productId) {
        try {
            Product product = productRepository.findByProductId(productId);
            if (product == null) {
                throw new ProductExceptionHandler.ProductNotFoundException("상품을 찾을 수 없습니다.");
            }
            return product;
        } catch (Exception e) {
            throw new RuntimeException("상품 조회 중 오류가 발생했습니다.", e);
        }
    }
}
