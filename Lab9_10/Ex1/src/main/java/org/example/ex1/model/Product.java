package org.example.ex1.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private Long id;
    private String code;
    private String productName;
    private double price;
    private String description;
    private String illustration;
}
