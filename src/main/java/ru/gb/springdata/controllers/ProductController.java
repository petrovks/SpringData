package ru.gb.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.dtos.ProductDto;
import ru.gb.springdata.exceptions.DataValidationException;
import ru.gb.springdata.exceptions.ResourceNotFoundException;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.services.CartService;
import ru.gb.springdata.services.CategoryService;
import ru.gb.springdata.services.ProductService;
import ru.gb.springdata.model.Category;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
   // private final CartService cartService;
// список всехпродуктов
    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10).map(ProductDto::new);
    }
//выбор продукта по id
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable(name = "id") Long id) {
        return new ProductDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found!")));
    }
    //добавление нового продукта

    @PostMapping
    public ProductDto save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }

        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found!"));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping
    public void update(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        productService.updateProduct(productDto);
    }
//удаление продукта
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        productService.delete(id);
    }

    //выбор продуктов с диапозоном цен
    @GetMapping("/between")
    public List<ProductDto> findAllByPriceBetween(@RequestParam(name = "min_price") int minPrice, @RequestParam(name = "max_price") int maxPrice) {
        return productService.findAllByPriceBetween(minPrice, maxPrice).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/more")
    public List<ProductDto> findAllByPriceGreaterThan(@RequestParam(name = "min_price") int minPrice) {
        return productService.findAllByPriceGreaterThan(minPrice).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/less")
    public List<ProductDto> findAllByPriceLessThan(@RequestParam(name = "max_price") int maxPrice) {
        return productService.findAllByPriceLessThan(maxPrice).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}/incrementCost")
    public ProductDto incrementCostById(@PathVariable(name = "id") Long id){
        return new ProductDto(productService.incrementCostById(id));
    }

    @GetMapping("/{id}/decrementCost")
    public ProductDto decrementCostById(@PathVariable(name = "id") Long id){
        return new ProductDto(productService.decrementCostById(id));
    }

//    @GetMapping("/addToCart/{id}")
//    public void addProductToCartById(@PathVariable(name = "id") Long id) {
//        cartService.addProductToCartById(productService.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException("Product with id = " + id + " not found!")));
//    }

}
