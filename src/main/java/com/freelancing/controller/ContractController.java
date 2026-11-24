
package com.freelancing.controller;

import com.freelancing.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/contracts")
    public String showContracts(Model model) {
        model.addAttribute("contracts", contractService.getAllContracts());
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
