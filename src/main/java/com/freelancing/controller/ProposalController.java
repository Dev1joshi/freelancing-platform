
package com.freelancing.controller;

import com.freelancing.model.Proposal;
import com.freelancing.service.ProposalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProposalController {

    private final ProposalService proposalService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @GetMapping("/proposals")
    public String showProposals(Model model) {
        model.addAttribute("proposals", proposalService.getAllProposals());
        return "proposals";
    }

    @GetMapping("/projects/{projectId}/proposals")
    public String showProjectProposals(
            @PathVariable int projectId, Model model) {

        model.addAttribute("proposals",
                proposalService.getProposalsByProject(projectId));

        return "proposals";
    }

    @GetMapping("/projects/{projectId}/proposals/new")
    public String showProposalForm(@PathVariable int projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "create-proposal";
    }

    @PostMapping("/proposals/create")
    public String submitProposal(@ModelAttribute Proposal proposal) {
        proposalService.submitProposal(proposal);
        return "redirect:/projects/" + proposal.getProjectId();
    }

    @PostMapping("/proposals/{id}/accept")
    public String acceptProposal(@PathVariable int id) {
        proposalService.acceptProposal(id);
        return "redirect:/proposals";
    }

    @PostMapping("/proposals/{id}/reject")
    public String rejectProposal(@PathVariable int id) {
        proposalService.rejectProposal(id);
        return "redirect:/proposals";
    }
}
