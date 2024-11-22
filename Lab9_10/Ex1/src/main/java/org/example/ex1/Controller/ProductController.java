package org.example.ex1.Controller;

import org.example.ex1.Service.ProductService;
import org.example.ex1.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/api/products")
    public ResponseEntity<String> addProduct( @RequestBody Product product) {
        if(productService.addProduct(product)) {
            return ResponseEntity.ok("Product added");
        }
        return ResponseEntity.badRequest().body("Product not added");
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if(product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);
        if(existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        existingProduct.setCode(product.getCode());
        existingProduct.setProductName(product.getProductName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setIllustration(product.getIllustration());
        if(productService.updateProduct(existingProduct)) {
            return ResponseEntity.ok("Product updated");
        }
        return ResponseEntity.badRequest().body("Product not updated");
    }

    @PatchMapping("/api/products/{id}")
    public ResponseEntity<String> updateProductPartially(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);
        if(existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        if(product.getCode() != null) {
            existingProduct.setCode(product.getCode());
        }
        if(product.getProductName() != null) {
            existingProduct.setProductName(product.getProductName());
        }
        if(product.getPrice() != 0) {
            existingProduct.setPrice(product.getPrice());
        }
        if(product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if(product.getIllustration() != null) {
            existingProduct.setIllustration(product.getIllustration());
        }
        if(productService.updateProduct(existingProduct)) {
            return ResponseEntity.ok("Product updated");
        }
        return ResponseEntity.badRequest().body("Product not updated");
    }
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Product existingProduct = productService.getProductById(id);
        if(existingProduct == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }



}
