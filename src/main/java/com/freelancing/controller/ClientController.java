
package com.freelancing.controller;

import com.freelancing.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String showClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }
}
