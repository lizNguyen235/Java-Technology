package org.example.ex1.Repo;

import org.example.ex1.model.OrderProductID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProduct extends JpaRepository<OrderProduct, OrderProductID> {
}
