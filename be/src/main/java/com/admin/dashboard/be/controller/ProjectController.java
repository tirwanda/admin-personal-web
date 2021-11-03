package com.admin.dashboard.be.controller;

import com.admin.dashboard.be.dto.ResponseData;
import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.service.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectController {
    private final ProjectServiceImpl projectService;

    @PostMapping("/project/{userId}")
    public ResponseEntity<ResponseData<Project>> saveProject(@PathVariable("userId") Long userId,
                                                             @Valid @RequestBody Project project,
                                                             Errors errors) {
        ResponseData<Project> responseData = new ResponseData<>();
        Project projectSave = projectService.saveProject(project);

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        projectService.addProjectToUser(userId, projectSave.getProjectId());
        responseData.setStatus(true);
        responseData.setPayload(projectSave);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/projects")
    public List<Project> findAllProject() {
        return projectService.getAllProjects();
    }

    @GetMapping("/project/{id}")
    public Project findProjectById(@PathVariable("id") Long projectId) {
        return projectService.getProject(projectId);
    }

    @PutMapping("/project")
    public ResponseEntity<ResponseData<Project>> updateProject(@Valid @RequestBody Project project, Errors errors) {
        ResponseData<Project> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(projectService.saveProject(project));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/project/{id}")
    public void removeProject(@PathVariable("id") Long projectId) {
        projectService.deleteProject(projectId);
    }
}
