package com.bluezone.payment.listener;

import com.bluezone.payment.model.Payment;
import com.bluezone.payment.model.PaymentMethod;
import com.bluezone.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class OrderEventListener {

    private final PaymentService paymentService;

    @Autowired
    public OrderEventListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "order-events", groupId = "payment-service-group")
    public void handleOrderEvent(Map<String, Object> orderData) {
        try {
            String eventType = (String) orderData.get("eventType");
            
            if ("order.created".equals(eventType)) {
                processOrderCreated(orderData);
            }
        } catch (Exception e) {
            System.err.println("Error processing order event: " + e.getMessage());
        }
    }

    private void processOrderCreated(Map<String, Object> orderData) {
        Long orderId = ((Number) orderData.get("id")).longValue();
        String customerId = (String) orderData.get("customerId");
        BigDecimal totalAmount = new BigDecimal(orderData.get("totalAmount").toString());

        // Create payment with default payment method
        Payment payment = new Payment(orderId, customerId, totalAmount, PaymentMethod.CREDIT_CARD);
        paymentService.processPayment(payment);
    }
}
