package org.example.springcommerce.Controller;

import org.example.springcommerce.Model.AccountPrincipal;
import org.example.springcommerce.Model.Cart;
import org.example.springcommerce.Model.MyOrder;
import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Service.CartService;
import org.example.springcommerce.Service.OrderService;
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
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @GetMapping("/order")
    public String orderManagement(@AuthenticationPrincipal AccountPrincipal accountPrincipal, Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Page<MyOrder> orderPage = orderService.getOrderByUserId(accountPrincipal.getId(), page, size);
        model.addAttribute("orders", orderPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());
        model.addAttribute("address", accountPrincipal.address());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(authority -> {
            model.addAttribute("role", authority.getAuthority());
        });
        model.addAttribute("username", authentication.getName());
        return "order";
    }

    @PostMapping("/order/add")
    @ResponseBody
    public ResponseEntity<String> addToOrder(@RequestParam Long cartId, @RequestParam String address) {
        Cart cart = cartService.findById(cartId);
        MyOrder myOrder = new MyOrder(cart,address);
        orderService.addToOrder(myOrder);
        return ResponseEntity.ok("Add to order successfully");
    }

    @PostMapping("/detail/order/add/{id}")
    @ResponseBody
    public ResponseEntity<String> addToOrderFromDetail(@PathVariable Long id, @RequestParam int quantity, @RequestParam String address, @AuthenticationPrincipal AccountPrincipal accountPrincipal) {
        Product product = productService.findById(id);
        MyOrder myOrder = new MyOrder(product, quantity, accountPrincipal.getId(), address);
        orderService.addToOrder(myOrder);
        return ResponseEntity.ok("Add to order successfully");
    }

}
