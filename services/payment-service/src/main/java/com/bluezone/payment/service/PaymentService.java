package com.bluezone.payment.service;

import com.bluezone.payment.model.Payment;
import com.bluezone.payment.model.PaymentStatus;
import com.bluezone.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final Random random = new Random();

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Payment processPayment(Payment payment) {
        payment.setStatus(PaymentStatus.PROCESSING);
        Payment savedPayment = paymentRepository.save(payment);

        // Simulate payment processing
        boolean paymentSuccessful = simulatePaymentProcessing();

        if (paymentSuccessful) {
            savedPayment.setStatus(PaymentStatus.COMPLETED);
            kafkaTemplate.send("payment-events", "payment.completed", savedPayment);
        } else {
            savedPayment.setStatus(PaymentStatus.FAILED);
            kafkaTemplate.send("payment-events", "payment.failed", savedPayment);
        }

        return paymentRepository.save(savedPayment);
    }

    public Optional<Payment> getPayment(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByOrder(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    public List<Payment> getPaymentsByCustomer(String customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }

    public Payment refundPayment(Long paymentId) {
        Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
        if (paymentOpt.isPresent()) {
            Payment payment = paymentOpt.get();
            if (payment.getStatus() == PaymentStatus.COMPLETED) {
                payment.setStatus(PaymentStatus.REFUNDED);
                Payment refundedPayment = paymentRepository.save(payment);
                
                kafkaTemplate.send("payment-events", "payment.refunded", refundedPayment);
                
                return refundedPayment;
            }
            throw new RuntimeException("Payment cannot be refunded. Current status: " + payment.getStatus());
        }
        throw new RuntimeException("Payment not found with id: " + paymentId);
    }

    // Simulate payment processing with 80% success rate
    private boolean simulatePaymentProcessing() {
        try {
            // Simulate processing time
            Thread.sleep(1000 + random.nextInt(2000));
            return random.nextDouble() < 0.8; // 80% success rate
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
