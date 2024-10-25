package org.example;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class Config {

    @Bean
    public Product beanOne() {
        Product product = new Product();
        product.setId(1);
        product.setName("Product 1");
        product.setPrice(100);
        product.setDescription("Description 1");
        return product;
    }

    @Bean
    public Product beanTwo() {
        return new Product(2, "Product 2", 200, "Description 2");
    }
    @Bean
    public Product beanThree() {
        return new Product(3, "Product 3", 300, "Description 3");
    }
}
