package org.example.ex1.Service;


import org.example.ex1.Repo.ProductRepo;
import org.example.ex1.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public boolean addProduct(Product product) {
        try {
            productRepo.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            productRepo.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
