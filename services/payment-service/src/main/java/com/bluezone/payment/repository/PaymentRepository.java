package com.bluezone.payment.repository;

import com.bluezone.payment.model.Payment;
import com.bluezone.payment.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByOrderId(Long orderId);
    
    List<Payment> findByCustomerId(String customerId);
    
    List<Payment> findByStatus(PaymentStatus status);
}
