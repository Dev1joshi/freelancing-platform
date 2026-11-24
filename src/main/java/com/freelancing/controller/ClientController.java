
package com.freelancing.controller;

import com.freelancing.model.Client;
import com.freelancing.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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

    @GetMapping("/clients/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "register-client";
    }

    @GetMapping("/register")
    public String showAccountSelection() {
        return "register";
    }

    @PostMapping("/clients/register")
    public String registerClient(@ModelAttribute Client client) {

        clientService.registerClient(client);

        return "redirect:/clients";
    }
}
