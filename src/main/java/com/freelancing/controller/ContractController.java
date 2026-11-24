
package com.freelancing.controller;

import com.freelancing.service.ContractService;
import com.freelancing.service.PaymentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ContractController {

    private final ContractService contractService;
    private final PaymentService paymentService;

    public ContractController(
            ContractService contractService,
            PaymentService paymentService) {

        this.contractService = contractService;
        this.paymentService = paymentService;
    }

    @GetMapping("/contracts")
    public String showContracts(
            Model model,
            @RequestParam(required = false) String paymentExists) {

        var contracts = contractService.getAllContracts();

        Map<Integer, String> paymentStatuses = new HashMap<>();

        for (var contract : contracts) {
            String status = paymentService.getContractPaymentStatus(
                    contract.getContractId());

            paymentStatuses.put(contract.getContractId(), status);
        }

        model.addAttribute("contracts", contracts);
        model.addAttribute("paymentStatuses", paymentStatuses);
        model.addAttribute("paymentExists", paymentExists != null);

        return "contracts";
    }

    @PostMapping("/contracts/{id}/status")
    public String updateContractStatus(
            @PathVariable int id,
            @RequestParam String status) {

        contractService.updateContractStatus(id, status);
        return "redirect:/contracts";
    }
}
