package ru.gb.springdata.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.springdata.dtos.ProductDto;
import ru.gb.springdata.model.Cart;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;

    public void addProductToCartById(ProductDto product) {
        //List<ProductDto> list = cart.getProductList();
        cart.getProductList().add(product);
      //  cart.setProductList(list);
    }

    public void deleteProductFromCart(ProductDto product) {
       // List<ProductDto> list = cart.getProductList();
        cart.getProductList().remove(product);
        //cart.setProductList(list);
    }

    public List<ProductDto> getCart() {
        return cart.getProductList();
    }

    public void deleteAllProductFromCart() {
        cart.clear();
    }
}
