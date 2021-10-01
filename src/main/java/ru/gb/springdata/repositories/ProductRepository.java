package ru.gb.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springdata.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    List<Product> findAllByPriceLessThanEqual(int maxPrice);
    List<Product> findProductByPriceGreaterThanEqual(int minPrice);
    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);

}
