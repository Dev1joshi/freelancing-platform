
package com.freelancing.controller;

import com.freelancing.model.Project;
import com.freelancing.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String showProjects(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "projects";
    }

    @GetMapping("/projects/new")
    public String showCreateProjectForm() {
        return "create-project";
    }

    @PostMapping("/projects/create")
    public String createProject(@ModelAttribute Project project) {
        projectService.createProject(project);
        return "redirect:/projects";
    }

    @GetMapping("/projects/{id}")
    public String showProjectDetails(
            @PathVariable int id, Model model) {

        Project project = projectService.getProjectById(id);

        if (project == null) {
            return "redirect:/projects";
        }

        model.addAttribute("project", project);
        return "project-details";
    }
}
