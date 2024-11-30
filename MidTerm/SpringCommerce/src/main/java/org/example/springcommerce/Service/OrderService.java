package org.example.springcommerce.Service;

import org.example.springcommerce.Model.MyOrder;
import org.example.springcommerce.Model.Product;
import org.example.springcommerce.Repo.OrderRepo;
import org.example.springcommerce.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRepo productRepo;
    public void addToOrder(MyOrder myOrder) {
        Product product = productRepo.findById(myOrder.getProductId()).get();
        product.setQuantity(product.getQuantity() - myOrder.getQuantity());
        productRepo.save(product);
        orderRepo.save(myOrder);
    }
    public Page<MyOrder> getOrderByUserId(Long id, int page, int size) {
        return orderRepo.findAllByUserId(id, PageRequest.of(page, size));
    }

    public void deleteOrder(Long id, Long productId) {
        Product product = productRepo.findById(productId).get();
        MyOrder order = orderRepo.findById(id).get();
        product.setQuantity(product.getQuantity() + order.getQuantity());
        productRepo.save(product);
        orderRepo.deleteById(id);
    }
}
