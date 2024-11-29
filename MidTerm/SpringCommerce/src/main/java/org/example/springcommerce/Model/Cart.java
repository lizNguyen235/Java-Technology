package org.example.springcommerce.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springcommerce.DTO.CartDTO;

@Data
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    public class Cart {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long productId;
        private Long userId;
        private String name;
        private int quantity;
        private int price;
        private int total;

        public Cart(CartDTO cartDTO) {
            this.productId = cartDTO.getProductId();
            this.userId = cartDTO.getUserId();
            this.name = cartDTO.getName();
            this.quantity = cartDTO.getQuantity();
            this.price = cartDTO.getPrice();
            this.total = cartDTO.getTotal();
        }
    }
