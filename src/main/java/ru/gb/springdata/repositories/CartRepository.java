package ru.gb.springdata.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.gb.springdata.dtos.ProductDto;
import ru.gb.springdata.model.Cart;
import ru.gb.springdata.model.Product;

import javax.annotation.PostConstruct;
import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartRepository {
    private Cart cart;
    private List<ProductDto> products;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public void addToCart(ProductDto product) {
        if(cart.getProductList() == null) {
            products = new ArrayList<>();
            cart.setProductList(products);
        }
        cart.getProductList().add(product);
    }

    public void deleteFromCart(ProductDto product) {
        if (cart.getProductList() == null) { return; }
        products = cart.getProductList();
        products.remove(product);
        cart.setProductList(products);
    }

    public List<ProductDto> cartInfo() {
        products = cart.getProductList();
        return products;
    }

    public void clearProductsList() {
        products = new ArrayList<>();
        cart.setProductList(products);
    }
}
