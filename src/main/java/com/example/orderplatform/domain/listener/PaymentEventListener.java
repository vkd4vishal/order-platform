package com.example.orderplatform.listener;

import com.example.orderplatform.domain.event.OrderCreatedEvent;
import com.example.orderplatform.domain.enums.OrderStatus;
import com.example.orderplatform.service.OrderService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PaymentEventListener {

    private final OrderService orderService;

    public PaymentEventListener(OrderService orderService) {
        this.orderService = orderService;
    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println("🔥 Payment triggered AFTER COMMIT for orderId = " + event.getOrderId());

        try {
            // Step 1: mark payment pending
            orderService.updateOrderStatus(
                    event.getOrderId(),
                    OrderStatus.PAYMENT_PENDING
            );

            // simulate payment processing
            Thread.sleep(2000);

            // Step 2: mark paid
            orderService.updateOrderStatus(
                    event.getOrderId(),
                    OrderStatus.PAID
            );

            System.out.println("✅ Order marked PAID: " + event.getOrderId());

        } catch (Exception e) {
            e.printStackTrace();

            // Step 3: mark failed (ONLY valid from PAYMENT_PENDING)
            orderService.updateOrderStatus(
                    event.getOrderId(),
                    OrderStatus.FAILED
            );
        }
    }
}
