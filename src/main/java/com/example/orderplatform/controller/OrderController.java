package com.example.orderplatform.controller;

import com.example.orderplatform.domain.entity.Order;
import com.example.orderplatform.dto.CreateOrderRequest;
import com.example.orderplatform.dto.UpdateOrderStatusRequest;
import com.example.orderplatform.service.OrderService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Validated
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {
        Order order = orderService.createOrder(
                request.getUserId(),
                request.getAmount()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(
            @PathVariable @Positive Long id
    ) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request
    ) {
        Order order = orderService.updateOrderStatus(id, request.getStatus());
        return ResponseEntity.ok(order);
    }

}
