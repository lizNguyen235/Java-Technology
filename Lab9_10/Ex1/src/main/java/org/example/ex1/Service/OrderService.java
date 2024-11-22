package org.example.ex1.Service;

import org.example.ex1.Repo.OrderRepo;
import org.example.ex1.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public boolean addOrder(Order order) {
        try {
            orderRepo.save(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public boolean updateOrder(Order existingOrder) {
        try {
            orderRepo.save(existingOrder);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
