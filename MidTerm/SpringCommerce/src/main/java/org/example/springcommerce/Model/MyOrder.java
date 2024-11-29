package org.example.springcommerce.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long userId;
    private String name;
    private int quantity;
    private int price;
    private int total;
    private String address;
    private String status;

    public MyOrder(Cart cart, String address) {
        this.productId = cart.getProductId();
        this.userId = cart.getUserId();
        this.name = cart.getName();
        this.quantity = cart.getQuantity();
        this.price = cart.getPrice();
        this.total = cart.getTotal();
        this.address = address;
        this.status = "Pending";
    }
    public MyOrder(Product product, int quantity, Long userId, String address) {
        this.productId = product.getId();
        this.userId = userId;
        this.name = product.getName();
        this.quantity = quantity;
        this.price = product.getPrice();
        this.total = product.getPrice() * quantity;
        this.address = address;
        this.status = "Pending";
    }
}
