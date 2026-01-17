package com.example.orderplatform.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.orderplatform.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAll(Pageable pageable);
}
