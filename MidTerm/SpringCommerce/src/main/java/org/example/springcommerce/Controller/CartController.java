package org.example.springcommerce.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springcommerce.DTO.CartDTO;
import org.example.springcommerce.Model.AccountPrincipal;
import org.example.springcommerce.Model.Cart;
import org.example.springcommerce.Service.CartService;
import org.example.springcommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CartController {

    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;
    @PostMapping("/cart/add/{id}")
    @ResponseBody
    public ResponseEntity<String> addToCart(@PathVariable("id") Long id, @RequestParam int quantity, @AuthenticationPrincipal AccountPrincipal accountPrincipal) {
        CartDTO cart = new CartDTO();
        cart.setProductId(id);
        cart.setUserId(accountPrincipal.getId());
        cart.setName(productService.findById(id).getName());
        cart.setPrice(productService.findById(id).getPrice());
        cart.setQuantity(quantity);
        cart.setTotal(cart.getPrice() * cart.getQuantity());
        Cart cartData = new Cart(cart);
        cartService.addToCart(cartData);
        return ResponseEntity.ok("Add to cart successfully");
    }

    @GetMapping("/cart")
    public String CartManagement(@AuthenticationPrincipal AccountPrincipal accountPrincipal, Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Page<Cart> cartPage = cartService.getCartByUserId(accountPrincipal.getId(), page, size);
        model.addAttribute("carts", cartPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", cartPage.getTotalPages());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(authority -> {
            model.addAttribute("role", authority.getAuthority());
        });
        model.addAttribute("username", authentication.getName());
        return "cart";
    }

    @DeleteMapping("/cart/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.ok("Delete cart successfully");
    }
}
