package org.example.ex1.Controller;

import org.example.ex1.Service.OrderService;
import org.example.ex1.model.Order;
import org.example.ex1.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/api/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> products = orderService.getAllOrders();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/api/orders")
    public ResponseEntity<String> addOrder( @RequestBody Order order) {
        if(orderService.addOrder(order)) {
            return ResponseEntity.ok("Order added");
        }
        return ResponseEntity.badRequest().body("Order not added");
    }

    @GetMapping("/api/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if(order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/api/orders/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order existingOrder = orderService.getOrderById(id);
        if(existingOrder == null) {
            return ResponseEntity.notFound().build();
        }
        existingOrder.setOrderNumber(order.getOrderNumber());
        existingOrder.setTotalSellingPrice(order.getTotalSellingPrice());
        if(orderService.updateOrder(existingOrder)) {
            return ResponseEntity.ok("Order updated");
        }
        return ResponseEntity.badRequest().body("Order not updated");
    }

    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        Order existingOrder = orderService.getOrderById(id);
        if(existingOrder == null) {
            return ResponseEntity.notFound().build();
        }
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted");
    }
}
