package org.example.springcommerce.Controller;

import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DetailController {

    @Autowired
    ProductService productService;
    @GetMapping("/detail/{id}")
    public String accessDetailPage(Model model, @PathVariable("id") Long id) {
        Product product = productService.findById(id); // Lấy dữ liệu sản phẩm từ service
        model.addAttribute("product", product);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(authority -> {
            model.addAttribute("role", authority.getAuthority());
        });
        model.addAttribute("username", authentication.getName());
        return "detail";
    }
}
