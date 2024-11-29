package org.example.springcommerce.Repo;

import org.example.springcommerce.Model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Page<Cart> findAllByUserId(Long id,  Pageable pageable);
}
