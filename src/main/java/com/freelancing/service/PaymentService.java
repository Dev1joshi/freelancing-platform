
package com.freelancing.service;

import com.freelancing.model.Payment;
import com.freelancing.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void createPayment(Payment payment) {

        if (payment.getAmount() == null ||
                payment.getAmount().signum() <= 0) {
            throw new IllegalArgumentException(
                    "Payment amount must be positive");
        }

        if (payment.getPaymentMethod() == null ||
                !List.of("UPI", "Bank Transfer", "Card")
                        .contains(payment.getPaymentMethod())) {
            throw new IllegalArgumentException(
                    "Invalid payment method");
        }

        if (paymentRepository.hasActivePayment(payment.getContractId())) {
            throw new IllegalArgumentException(
                    "A pending or completed payment already exists for this contract");
        }

        paymentRepository.save(payment);
    }

    public String getContractPaymentStatus(int contractId) {
        return paymentRepository.getContractPaymentStatus(contractId);
    }

    public void updatePaymentStatus(int paymentId, String status) {

        if (!List.of("PENDING", "COMPLETED", "FAILED").contains(status)) {
            throw new IllegalArgumentException(
                    "Invalid payment status");
        }

        paymentRepository.updateStatus(paymentId, status);
    }
}
