
package com.freelancing.controller;

import com.freelancing.service.FreelancerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
