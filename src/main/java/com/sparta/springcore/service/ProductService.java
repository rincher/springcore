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
    private static final int MIN_PRICE = 0;

    public List<Product> getProducts(){
        // 멤버 변수 사용
        return productRepository.findAll();
    }
    @Transactional
    public Product createProduct(ProductRequestDto requestDto){
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);
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
        product.updateMyPrice(requestDto);
        return product;
    }
}