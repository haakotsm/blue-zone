package com.bluezone.order.repository;

import com.bluezone.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByCustomerId(String customerId);
    
    List<Order> findByStatus(com.bluezone.order.model.OrderStatus status);
}
