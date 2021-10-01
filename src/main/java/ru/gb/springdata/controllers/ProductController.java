package ru.gb.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.dto.ProductDto;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.services.CategoryService;
import ru.gb.springdata.services.ProductService;
import ru.gb.springdata.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
// список всехпродуктов
    @GetMapping("/products")
    public List<ProductDto> findAll() {
        return productService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }
//выбор продукта по id
    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
    }
    //добавление нового продукта
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
//удаление продукта
    @GetMapping("delete/product/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    //выбор продуктов с диапозоном цен
    @GetMapping("products/between")
    public List<ProductDto> findAllByPriceBetween(@RequestParam(name = "min_price") int minPrice, @RequestParam(name = "max_price") int maxPrice) {
        return productService.findAllByPriceBetween(minPrice, maxPrice).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("products/more")
    public List<ProductDto> findAllByPriceGreaterThan(@RequestParam(name = "min_price") int minPrice) {
        return productService.findAllByPriceGreaterThan(minPrice).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("products/less")
    public List<ProductDto> findAllByPriceLessThan(@RequestParam(name = "max_price") int maxPrice) {
        return productService.findAllByPriceLessThan(maxPrice).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/product/{id}/incrementCost")
    public ProductDto incrementCostById(@PathVariable Long id){
        return new ProductDto(productService.incrementCostById(id));
    }

    @GetMapping("/product/{id}/decrementCost")
    public ProductDto decrementCostById(@PathVariable Long id){
        return new ProductDto(productService.decrementCostById(id));
    }

}
