package ru.gb.springdata.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.springdata.model.OrderItem;
import ru.gb.springdata.model.Product;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.price = product.getPrice();
        this.pricePerProduct = product.getPrice();
    }

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.pricePerProduct = orderItem.getPricePerProduct();
    }

    public void changeQuantity(int delta) {
        quantity += delta;
        if (quantity < 0) {
            quantity = 0;
        }
        price = pricePerProduct * quantity;
    }
}
