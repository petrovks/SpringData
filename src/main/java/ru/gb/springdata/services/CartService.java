package ru.gb.springdata.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.springdata.dtos.ProductDto;
import ru.gb.springdata.exceptions.ResourceNotFoundException;
import ru.gb.springdata.model.Cart;
import ru.gb.springdata.model.Product;
import ru.gb.springdata.repositories.CartRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void addProductToCartById(ProductDto product) {
        cartRepository.addToCart(product);
    }

    public void deleteProductFromCart(ProductDto product) {
        cartRepository.deleteFromCart(product);
    }

    public List<ProductDto> cartInfo() {
        return cartRepository.cartInfo();
    }

    public void deleteAllProductFromCart() {
        cartRepository.clearProductsList();
    }
}
