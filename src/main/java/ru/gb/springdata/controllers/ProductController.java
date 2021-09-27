package ru.gb.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.dto.ProductDto;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.services.CategoryService;
import ru.gb.springdata.services.ProductService;
import ru.gb.springdata.model.Category;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping("/products")
    public List<ProductDto> findAll() {
        List<Product> products = productService.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p: products) {
            productDtos.add(new ProductDto(p));
        }
        return productDtos;
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
    }

    @PostMapping("/new_product")
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).get();
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @GetMapping("delete/product/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("products/between")
    public List<ProductDto> findAllByPriceBetween(@RequestParam(name = "min_price") int minPrice, @RequestParam(name = "max_price") int maxPrice) {
        List<Product> products = productService.findAllByPriceBetween(minPrice, maxPrice);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p: products) {
            productDtos.add(new ProductDto(p));
        }
        return productDtos;
    }

    @GetMapping("products/more")
    public List<ProductDto> findAllByPriceGreaterThan(@RequestParam(name = "min_price") int minPrice) {
        List<Product> products = productService.findAllByPriceGreaterThan(minPrice);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p: products) {
            productDtos.add(new ProductDto(p));
        }
        return productDtos;
    }

    @GetMapping("products/less")
    public List<ProductDto> findAllByPriceLessThan(@RequestParam(name = "max_price") int maxPrice) {
        List<Product> products = productService.findAllByPriceLessThan(maxPrice);
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product p: products) {
            productDtos.add(new ProductDto(p));
        }
        return productDtos;
    }
}
