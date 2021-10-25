package ru.gb.springdata.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
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


    public Product incrementCostById(Long id) {
        Product product = findById(id).get();
        product.setPrice(product.getPrice() + 1);
        save(product);
        return product;
    }

    public Product decrementCostById(Long id) {
        Product product = findById(id).get();
        product.setPrice(product.getPrice() - 1);
        save(product);
        return product;
    }
}
