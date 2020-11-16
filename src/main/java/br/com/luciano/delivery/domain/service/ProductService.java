package br.com.luciano.delivery.domain.service;

import br.com.luciano.delivery.domain.entity.ProductEntity;
import br.com.luciano.delivery.domain.exception.ProductNotFoundException;
import br.com.luciano.delivery.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity findOrFail(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }
}
