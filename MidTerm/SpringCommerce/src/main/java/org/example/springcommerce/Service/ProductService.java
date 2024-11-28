package org.example.springcommerce.Service;


import jakarta.servlet.http.HttpServletRequest;
import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class ProductService  {
    @Autowired
    ProductRepo productRepo;

    public boolean addProduct(Product product) {
        productRepo.save(product);
        return true;
    }

    public Page<Product> getProducts(int page, int size) {
        return productRepo.findAll(PageRequest.of(page, size));
    }

    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id).get();
        String filePath = product.getImage();
        File file = new File(filePath);
        file.delete();
        productRepo.deleteById(id);
    }

    public void updateProduct(Product newProduct) {
        Product product = productRepo.findById(newProduct.getId()).get();
        String filePath = product.getImage();
        File file = new File(filePath);
        file.delete();
        productRepo.save(newProduct);
    }

    public List<String> getCategories() {
        return productRepo.findDistinctCategory();
    }

    public List<String> getBrands() {
        return productRepo.findDistinctBrands();
    }

    public List<String> getColors() {
        return productRepo.findDistinctColors();
    }
    public Page<Product> findAllByCondition(String brand, String color, String category, String minPrice, String maxPrice, int page, int size) {
        if(minPrice.isEmpty()) {
            minPrice = "0";

        }
        if(maxPrice.isEmpty()) {
            maxPrice = "999999999";
        }
        double max = Double.parseDouble(maxPrice);
        double min = Double.parseDouble(minPrice);
        if(brand.isEmpty() && !color.isEmpty() && !category.isEmpty()) {
            return productRepo.findAllByColorAndCategoryAndPriceBetween(color, category, min, max, PageRequest.of(page, size));
        }
        if(!brand.isEmpty() && color.isEmpty() && !category.isEmpty()) {
            return productRepo.findAllByBrandAndCategoryAndPriceBetween(brand, category, min, max, PageRequest.of(page, size));
        }
        if(!brand.isEmpty() && !color.isEmpty() && category.isEmpty()) {
            return productRepo.findAllByBrandAndColorAndPriceBetween(brand, color, min, max, PageRequest.of(page, size));
        }
        if(!brand.isEmpty() && color.isEmpty()) {
            return productRepo.findAllByBrandAndPriceBetween(brand, min, max, PageRequest.of(page, size));
        }
        if(brand.isEmpty() && !color.isEmpty()) {
            return productRepo.findAllByColorAndPriceBetween(color, min, max, PageRequest.of(page, size));
        }
        if(brand.isEmpty() && !category.isEmpty()) {
            return productRepo.findAllByCategoryAndPriceBetween(category, min, max, PageRequest.of(page, size));
        }
        if(brand.isEmpty()) {
            return productRepo.findAllByPriceBetween(min, max, PageRequest.of(page, size));
        }
        return productRepo.findAllByBrandAndColorAndCategoryAndPriceBetween(brand, color, category, min, max, PageRequest.of(page, size));
    }
}
