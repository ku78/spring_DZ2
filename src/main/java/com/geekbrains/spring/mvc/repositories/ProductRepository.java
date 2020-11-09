package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Component
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    public ProductRepository() {
    }

    @PostConstruct
    public void initialize() {
        productList.addAll(Arrays.asList(
                new Product(1l, "Халва", 100),
                new Product(2l, "Мармелад", 200),
                new Product(3l, "Инжир", 300),
                new Product(4l, "Чак-чак", 400),
                new Product(5l, "Чурчхела", 500)
        ));

    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public Product getProductById(long id) {
        return productList.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    public void save(Product product) {
        productList.add(product);
    }

    public void deleteById(long id) {
        productList.removeIf(p -> p.getId() == id);
    }

    public long getNextId() {
        return productList.stream().mapToLong(p -> p.getId()).max().getAsLong() + 1l;
    }

    @Override
    public String toString() {

        if(productList.isEmpty()){
            return "Список товаров пуст";
        }

        StringBuilder str = new StringBuilder();
        for (Product p:productList) {
            str.append(p).append("\n");
        }
        return str.toString();
    }
}