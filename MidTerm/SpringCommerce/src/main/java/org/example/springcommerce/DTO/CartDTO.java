package org.example.springcommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private Long productId;
    private Long userId;
    private String name;
    private int quantity;
    private int price;
    private int total;
}
