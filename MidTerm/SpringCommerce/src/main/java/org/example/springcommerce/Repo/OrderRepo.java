package org.example.springcommerce.Repo;

import org.example.springcommerce.Model.MyOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<MyOrder, Long> {

    Page<MyOrder> findAllByUserId(Long id, Pageable pageable);
}
