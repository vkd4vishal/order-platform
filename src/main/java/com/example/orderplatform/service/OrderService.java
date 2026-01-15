package com.example.orderplatform.service;

import com.example.orderplatform.domain.entity.Order;
import com.example.orderplatform.domain.enums.OrderStatus;
import com.example.orderplatform.exception.OrderNotFoundException;
import com.example.orderplatform.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order createOrder(Long userId, BigDecimal amount) {
        Order order = new Order(
                userId,
                amount,
                OrderStatus.CREATED
        );

        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        order.changeStatus(newStatus);
        return order;
    }

}
