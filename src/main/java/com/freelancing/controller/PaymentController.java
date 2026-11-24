
package com.freelancing.controller;

import com.freelancing.model.Payment;
import com.freelancing.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public String showPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payments";
    }

    @PostMapping("/payments/create")
    public String createPayment(@ModelAttribute Payment payment) {
        paymentService.createPayment(payment);
        return "redirect:/payments";
    }

    @PostMapping("/payments/{id}/status")
    public String updatePaymentStatus(
            @PathVariable int id,
            @RequestParam String status) {

        paymentService.updatePaymentStatus(id, status);
        return "redirect:/payments";
    }
}
