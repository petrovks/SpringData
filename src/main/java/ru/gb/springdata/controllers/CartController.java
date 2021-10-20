package ru.gb.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.dtos.ProductDto;
import ru.gb.springdata.exceptions.ResourceNotFoundException;
import ru.gb.springdata.model.Cart;
import ru.gb.springdata.services.CartService;
import ru.gb.springdata.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCartForCurrentUser() {
       return cartService.getCartForCurrentUser();
    }

    @GetMapping("/remove/all")
    public void deleteAllFromCart() {
        cartService.clearCart();
    }

    @GetMapping("/remove/{productId}")
    public void deleteProductFromCartById(@PathVariable Long productId) {
        cartService.deleteProductFromCart(productId);
    }

    @GetMapping("/add/{productId}")
    public void addProductToCartById(@PathVariable Long productId) {
        cartService.addItem(productId);
    }

    @GetMapping("/decrement/{productId}")
    public void decrementItem(@PathVariable Long productId) {
        cartService.decrementItem(productId);
    }

}
