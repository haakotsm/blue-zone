package com.bluezone.order.service;

import com.bluezone.order.model.Order;
import com.bluezone.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        
        // Send order created event to Kafka
        kafkaTemplate.send("order-events", "order.created", savedOrder);
        
        return savedOrder;
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomer(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order updateOrderStatus(Long orderId, com.bluezone.order.model.OrderStatus status) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setStatus(status);
            Order updatedOrder = orderRepository.save(order);
            
            // Send order status updated event to Kafka
            kafkaTemplate.send("order-events", "order.status.updated", updatedOrder);
            
            return updatedOrder;
        }
        throw new RuntimeException("Order not found with id: " + orderId);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
