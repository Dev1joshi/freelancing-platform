
package com.freelancing.service;

import com.freelancing.model.Freelancer;
import com.freelancing.repository.FreelancerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;

    public FreelancerService(FreelancerRepository freelancerRepository) {
        this.freelancerRepository = freelancerRepository;
    }

    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    public Freelancer getFreelancerById(int id) {
        return freelancerRepository.findById(id);
    }

    public void registerFreelancer(Freelancer freelancer) {
        freelancerRepository.save(freelancer);
    }
}
