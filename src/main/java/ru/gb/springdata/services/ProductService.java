package ru.gb.springdata.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springdata.dtos.ProductDto;
import ru.gb.springdata.exceptions.ResourceNotFoundException;
import ru.gb.springdata.model.Category;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

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

    @Transactional
    public void updateProduct(ProductDto productDto){
        Product product = findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product with id = " +  productDto.getId() + " not found!"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        if (!product.getCategory().getTitle().equals(productDto.getCategoryTitle())) {
            Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category " + productDto.getCategoryTitle() + " not found!"));
            product.setCategory(category);
        }
    }

}
