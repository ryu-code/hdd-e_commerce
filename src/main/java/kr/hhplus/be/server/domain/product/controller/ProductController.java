package kr.hhplus.be.server.domain.product.controller;

import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class ProductController {

    private final ProductService productService;

    // 상품 조회
    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId) {

        return productService.getProduct(productId);
    }
}