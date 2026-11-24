
package com.freelancing.service;

import com.freelancing.model.Project;
import com.freelancing.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id);
    }

    public List<Project> getProjectsByClient(int clientId) {
        return projectRepository.findByClientId(clientId);
    }

    public void createProject(Project project) {
        if (project.getBudget() == null || project.getBudget().signum() <= 0) {
            throw new IllegalArgumentException("Budget must be greater than zero");
        }

        projectRepository.save(project);
    }
}
