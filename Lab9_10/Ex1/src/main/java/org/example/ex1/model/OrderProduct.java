package org.example.ex1.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderProductID.class)
public class OrderProduct {
    @Id
    private Long orderId;
    @Id
    private Long productId;
    private int quantity;
    private double sellingPrice;
}
