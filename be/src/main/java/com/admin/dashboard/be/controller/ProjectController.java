package com.admin.dashboard.be.controller;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.service.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectController {
    private final ProjectServiceImpl projectService;

    @PostMapping("/project")
    public Project saveProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/projects")
    public List<Project> findAllProject() {
        return projectService.getAllProjects();
    }

    @GetMapping("/project/{id}")
    public Project findProjectByTitle(@PathVariable("id") Long projectId) {
        return projectService.getProject(projectId);
    }

    @PutMapping("/project")
    public Project updateProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @DeleteMapping("/project/{id}")
    public void removeProject(@PathVariable("id") Long projectId) {
        projectService.deleteProject(projectId);
    }
}
