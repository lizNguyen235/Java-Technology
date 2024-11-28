package org.example.springcommerce.Controller;


import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @GetMapping("/home")
    public String accessHomePage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(authority -> {
            model.addAttribute("role", authority.getAuthority());
        });
        Page<Product> productPage = productService.getProducts(page, size);
        List<String> categories = productService.getCategories();
        model.addAttribute("categories", categories);
        List<String> brands = productService.getBrands();
        model.addAttribute("brands", brands);
        List<String> colors = productService.getColors();
        model.addAttribute("colors", colors);
        model.addAttribute("productPage", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("username", authentication.getName());
        return "homepage";
    }

    @GetMapping("/home/filter")
    public String filterProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, @RequestParam String category, @RequestParam String brand, @RequestParam String color,@RequestParam String minPrice,@RequestParam String maxPrice, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(authority -> {
            model.addAttribute("role", authority.getAuthority());
        });
        if (category.isEmpty()) {
            category = "All";
        }
        if (brand.isEmpty()) {
            brand = "All";
        }
        if (color.isEmpty()) {
            color = "All";
        }

        // Thêm giá trị category vào model
        model.addAttribute("selectedCategory", category);
        model.addAttribute("selectedColor", color);
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("selectedMinPrice", minPrice);
        model.addAttribute("selectedMaxPrice", maxPrice);
        Page<Product> productPage = productService.findAllByCondition(brand, color, category, minPrice, maxPrice, page, size);
        List<String> categories = productService.getCategories();
        model.addAttribute("categories", categories);
        List<String> brands = productService.getBrands();
        model.addAttribute("brands", brands);
        List<String> colors = productService.getColors();
        model.addAttribute("colors", colors);
        model.addAttribute("productPage", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("username", authentication.getName());
        return "homepage";
    }
}
