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
    private String description;
    private double price;
    @Lob
    private byte[] image;
    private int quantity;
    private String category;
    private String brand;
    private String color;

    public Product(ProductDTO product, byte[] image) {
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
