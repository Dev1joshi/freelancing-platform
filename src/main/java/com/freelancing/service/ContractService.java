
package com.freelancing.service;

import com.freelancing.model.Contract;
import com.freelancing.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract getContractById(int contractId) {
        return contractRepository.findById(contractId);
    }

    public void updateContractStatus(int contractId, String status) {
        if (!List.of("ACTIVE", "COMPLETED", "TERMINATED").contains(status)) {
            throw new IllegalArgumentException("Invalid contract status");
        }

        contractRepository.updateStatus(contractId, status);
    }
}
