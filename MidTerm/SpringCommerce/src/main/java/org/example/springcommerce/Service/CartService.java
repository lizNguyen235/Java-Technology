package org.example.springcommerce.Service;

import org.example.springcommerce.Model.Cart;
import org.example.springcommerce.Repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepo;
    public void addToCart(Cart cart) {
        cartRepo.save(cart);
    }

    public Page<Cart> getCartByUserId(Long id, int page, int size) {
        return cartRepo.findAllByUserId(id, PageRequest.of(page, size));
    }
}
