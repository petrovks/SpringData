package ru.gb.springdata.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByPriceBetween(int minPrice, int maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findAllByPriceGreaterThan(int minPrice) {
        return productRepository.findProductByPriceGreaterThanEqual(minPrice);
    }

    public List<Product> findAllByPriceLessThan(int maxPrice) {
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
