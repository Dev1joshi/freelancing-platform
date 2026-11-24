
package com.freelancing.controller;

import com.freelancing.model.Payment;
import com.freelancing.model.Contract;
import com.freelancing.service.PaymentService;
import com.freelancing.service.ContractService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {

    private final PaymentService paymentService;
    private final ContractService contractService;

    public PaymentController(
            PaymentService paymentService,
            ContractService contractService) {

        this.paymentService = paymentService;
        this.contractService = contractService;
    }

    @GetMapping("/payments")
    public String showPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payments";
    }

    @GetMapping("/payments/create/{contractId}")
    public String showPaymentForm(
            @PathVariable int contractId,
            Model model) {

        Contract contract = contractService.getContractById(contractId);

        if (contract == null || !"ACTIVE".equals(contract.getStatus())) {
            return "redirect:/contracts";
        }

        String paymentStatus =
                paymentService.getContractPaymentStatus(contractId);

        if ("PENDING".equals(paymentStatus) ||
                "COMPLETED".equals(paymentStatus)) {
            return "redirect:/contracts?paymentExists";
        }

        Payment payment = new Payment();
        payment.setContractId(contract.getContractId());
        payment.setAmount(contract.getAgreedAmount());

        model.addAttribute("payment", payment);
        model.addAttribute("contract", contract);

        return "create-payment";
    }

    @PostMapping("/payments/create")
    public String createPayment(@ModelAttribute Payment payment) {

        Contract contract =
                contractService.getContractById(payment.getContractId());

        if (contract == null || !"ACTIVE".equals(contract.getStatus())) {
            return "redirect:/contracts";
        }

        payment.setAmount(contract.getAgreedAmount());

        try {
            paymentService.createPayment(payment);
        } catch (IllegalArgumentException e) {
            return "redirect:/contracts?paymentExists";
        }

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
