package ru.gb.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.dtos.ProductDto;
import ru.gb.springdata.exceptions.ResourceNotFoundException;
import ru.gb.springdata.services.CartService;
import ru.gb.springdata.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> cartInfo() {
       return cartService.cartInfo();
    }

    @GetMapping("/delete/all")
    public void deleteAllFromCart() {
        cartService.deleteAllProductFromCart();
    }

    @GetMapping("/delete/{id}")
    public void deleteProductFromCartById(@PathVariable(name = "id") Long id) {
        ProductDto productDto = new ProductDto(productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id = " + id + " can not deleted from cart.")));
        cartService.deleteProductFromCart(productDto);
    }

    @GetMapping("/addToCart/{id}")
    public void addProductToCartById(@PathVariable(name = "id") Long id) {
        ProductDto productDto = new ProductDto(productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id = " + id + " can not deleted from cart.")));
        cartService.addProductToCartById(productDto);
    }

}
