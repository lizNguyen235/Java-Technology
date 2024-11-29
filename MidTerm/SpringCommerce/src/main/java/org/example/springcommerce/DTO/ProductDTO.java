package org.example.springcommerce.DTO;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String description;
    private int price;
    private int quantity;
    private String category;
    private String brand;
    private String color;
}
