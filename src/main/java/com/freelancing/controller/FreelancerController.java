
package com.freelancing.controller;

import com.freelancing.model.Freelancer;
import com.freelancing.service.FreelancerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class FreelancerController {

    private final FreelancerService freelancerService;

    public FreelancerController(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    @GetMapping("/freelancers")
    public String showFreelancers(Model model) {
        model.addAttribute("freelancers",
                freelancerService.getAllFreelancers());

        return "freelancers";
    }

    @GetMapping("/freelancers/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("freelancer", new Freelancer());
        return "register-freelancer";
    }

    @PostMapping("/freelancers/register")
    public String registerFreelancer(
            @ModelAttribute Freelancer freelancer) {

        freelancerService.registerFreelancer(freelancer);

        return "redirect:/freelancers";
    }
}
