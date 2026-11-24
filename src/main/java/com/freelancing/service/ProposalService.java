
package com.freelancing.service;

import com.freelancing.model.Proposal;
import com.freelancing.repository.ProposalRepository;
import com.freelancing.repository.ProjectRepository;
import com.freelancing.repository.ContractRepository;
import com.freelancing.model.Contract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final ProjectRepository projectRepository;
    private final ContractRepository contractRepository;

    public ProposalService(
            ProposalRepository proposalRepository,
            ProjectRepository projectRepository,
            ContractRepository contractRepository) {

        this.proposalRepository = proposalRepository;
        this.projectRepository = projectRepository;
        this.contractRepository = contractRepository;
    }

    public List<Proposal> getAllProposals() {
        return proposalRepository.findAll();
    }

    public List<Proposal> getProposalsByProject(int projectId) {
        return proposalRepository.findByProjectId(projectId);
    }

    public void submitProposal(Proposal proposal) {
        if (proposal.getProposedAmount() == null ||
                proposal.getProposedAmount().signum() <= 0) {
            throw new IllegalArgumentException("Proposal amount must be positive");
        }

        proposalRepository.save(proposal);
    }

    @Transactional
    public void acceptProposal(int proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId);

        if (proposal == null || !"PENDING".equals(proposal.getStatus())) {
            throw new IllegalArgumentException("Proposal is not available");
        }

        var project = projectRepository.findById(proposal.getProjectId());

        if (project == null || !"OPEN".equals(project.getStatus())) {
            throw new IllegalArgumentException("Project is not open");
        }

        proposalRepository.updateStatus(proposalId, "ACCEPTED");

        proposalRepository.rejectOtherProposals(
                proposal.getProjectId(), proposalId);

        projectRepository.updateStatus(
                proposal.getProjectId(), "IN_PROGRESS");

        Contract contract = new Contract();
        contract.setProposalId(proposalId);
        contract.setStartDate(LocalDate.now());
        contract.setEndDate(project.getDeadline());
        contract.setAgreedAmount(proposal.getProposedAmount());

        contractRepository.save(contract);
    }

    public void rejectProposal(int proposalId) {
        Proposal proposal = proposalRepository.findById(proposalId);

        if (proposal == null || !"PENDING".equals(proposal.getStatus())) {
            throw new IllegalArgumentException("Proposal is not available");
        }

        proposalRepository.updateStatus(proposalId, "REJECTED");
    }
}
