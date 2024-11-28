package org.example.springcommerce.Repo;

import org.example.springcommerce.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategory();
    @Query("SELECT DISTINCT p.brand FROM Product p")
    List<String> findDistinctBrands();
    @Query("SELECT DISTINCT p.color FROM Product p")
    List<String> findDistinctColors();
    Page<Product> findAllByBrandAndColorAndCategoryAndPriceBetween(String brand, String color, String category, double minPrice, double maxPrice, Pageable pageable);
    Page<Product> findAllByColorAndCategoryAndPriceBetween(String color, String category, double min, double max, Pageable pageable);
    Page<Product> findAllByBrandAndCategoryAndPriceBetween(String brand, String category, double min, double max, Pageable pageable);
    Page<Product> findAllByBrandAndColorAndPriceBetween(String brand, String color, double min, double max, Pageable pageable);
    Page<Product> findAllByBrandAndPriceBetween(String brand, double min, double max, Pageable pageable);
    Page<Product> findAllByColorAndPriceBetween(String color, double min, double max, Pageable pageable);
    Page<Product> findAllByCategoryAndPriceBetween(String category, double min, double max, Pageable pageable);
    Page<Product> findAllByPriceBetween(double min, double max, Pageable pageable);
}
