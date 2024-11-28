package org.example.springcommerce.Controller;

import jakarta.security.auth.message.callback.PrivateKeyCallback;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.example.springcommerce.DTO.ProductDTO;
import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/product")
    public String ProductManagement(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        Page<Product> productPage = productService.getProducts(page, size);
        model.addAttribute("productPage", productPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "product";
    }

    @PostMapping("/product/add")
    public String CreateProduct(Model model, @ModelAttribute ProductDTO product, @RequestParam("image") MultipartFile imageFile, HttpServletRequest request) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        String filePath = request.getServletContext().getRealPath("/");
        Random random = new Random();
        String img = filePath +random.nextInt(1000)+imageFile.getOriginalFilename();
        imageFile.transferTo(new File(img));
        Product newProduct = new Product(product, img);
        // Save product to database
        productService.addProduct(newProduct);
        return "redirect:/product";
    }

    @DeleteMapping("/product/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> DeleteProduct(Model model, @PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }

    @PostMapping("/product/update/{id}")
    @ResponseBody
    public ResponseEntity<String> UpdateProduct(Model model, @PathVariable Long id, @ModelAttribute ProductDTO product, @RequestParam("image") MultipartFile imageFile, HttpServletRequest request) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        String filePath = request.getServletContext().getRealPath("/");
        Random random = new Random();
        String img = filePath +random.nextInt(1000)+imageFile.getOriginalFilename();
        imageFile.transferTo(new File(img));
        Product newProduct = new Product(product, img);
        newProduct.setId(id);
        productService.updateProduct(newProduct);
        return ResponseEntity.ok("Product updated");
    }
}
