package com.sparta.springcore.service;
import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.model.Product;
import com.sparta.springcore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductService {
    // 멤버 변수 선언
    private final ProductRepository productRepository;
    private static final int MIN_PRICE = 100;

    public List<Product> getProducts(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Product createProduct(ProductRequestDto requestDto, Long userId ) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto, userId);
        productRepository.save(product);
        return product;
    }

    @Transactional
    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id not found")
        );
        int myPrice = requestDto.getMyprice();
        if (myPrice < MIN_PRICE){
            throw new IllegalArgumentException("최소 가격이 0 이하 일 수 없습니다.");
        }
        product.updateMyPrice(myPrice);
        return product;
    }
}