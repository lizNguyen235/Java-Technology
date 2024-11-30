package org.example.springcommerce.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springcommerce.DTO.ProductDTO;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 10000)
    private String description;
    private int price;
    private String image;
    private int quantity;
    private String category;
    private String brand;
    private String color;

    public Product(ProductDTO product, String image) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.category = product.getCategory();
        this.brand = product.getBrand();
        this.color = product.getColor();
        this.image = image;
    }

}
