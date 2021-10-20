package ru.gb.springdata.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.springdata.dtos.ProductDto;
import ru.gb.springdata.exceptions.ResourceNotFoundException;
import ru.gb.springdata.model.Cart;
import ru.gb.springdata.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        this.cart = new Cart();
    }

    public Cart getCartForCurrentUser() {
        return cart;
    }

    public void addItem(Long productId) {
        if (cart.add(productId)) {
            return;
        }
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину, так как продукт с id: " + productId + " не существует"));
        cart.add(product);
    }

    public void deleteProductFromCart(Long productId) {
       cart.remove(productId);
    }

    public void clearCart() {
        cart.clear();
    }

    public void decrementItem(Long productId) {
        cart.decrement(productId);
    }
}
