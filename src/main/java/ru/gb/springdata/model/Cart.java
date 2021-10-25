package ru.gb.springdata.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.gb.springdata.dtos.ProductDto;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@Scope("prototype")
public class Cart {
    private List<ProductDto> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>();
    }

}
