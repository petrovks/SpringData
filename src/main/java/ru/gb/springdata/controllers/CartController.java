package ru.gb.springdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdata.dtos.StringResponse;
import ru.gb.springdata.dtos.ProductDto;
import ru.gb.springdata.exceptions.ResourceNotFoundException;
import ru.gb.springdata.model.Cart;
import ru.gb.springdata.services.CartService;
import ru.gb.springdata.services.ProductService;

import java.security.Principal;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/generate")
    public StringResponse generateCartUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/merge")
    public void mergeCarts(Principal principal, @PathVariable String uuid) {
        // TODO кто-нибудь может додуматься это вызвать без токена
        cartService.merge(principal, uuid);
    }

    @GetMapping("/{uuid}")
    public Cart getCartForCurrentUser(Principal principal, @PathVariable String uuid) {
        return cartService.getCartForCurrentUser(principal, uuid);
    }

    @GetMapping("/{uuid}/remove/all")
    public void deleteAllFromCart(Principal principal, @PathVariable String uuid) {
        cartService.clearCart(principal, uuid);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void deleteProductFromCartById(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItem(principal, uuid, productId);
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void addProductToCartById(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.addItem(principal, uuid, productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrementItem(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(principal, uuid, productId);
    }

    @GetMapping("/{uuid}/increment/{productId}")
    public void incrementItem(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.incrementItem(principal, uuid, productId);
    }
}
