package com.admin.dashboard.be.controller;

import com.admin.dashboard.be.dto.ProjectDTO;
import com.admin.dashboard.be.dto.ResponseData;
import com.admin.dashboard.be.dto.SearchData;
import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.service.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectController {

    private final ProjectServiceImpl projectService;
    private final ModelMapper modelMapper;

    @PostMapping("/project/{userId}")
    public ResponseEntity<ResponseData<Project>> saveProject(@PathVariable("userId") Long userId,
                                                             @Valid @RequestBody ProjectDTO projectDTO,
                                                             Errors errors) {
        ResponseData<Project> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Project project = modelMapper.map(projectDTO, Project.class);
        Project projectSave = projectService.saveProject(project);
        projectService.addProjectToUser(userId, projectSave.getProjectId());
        responseData.setStatus(true);
        responseData.setPayload(projectSave);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/search/project/by-title-contains/{size}/{page}/{sort}")
    public Iterable<Project> getProjectByTitleContains(@RequestBody SearchData searchData,
                                                       @PathVariable("size") Integer size,
                                                       @PathVariable("page") Integer page,
                                                       @PathVariable("sort") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("projectId"));
        if (sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("projectId").descending());
        }
        return projectService.getProjectByTitleContains(searchData.getSearchKey(), pageable);
    }

    @PostMapping("/upload/images/{projectId}")
    public ResponseEntity<ResponseData<Project>> uploadImages(@PathVariable("projectId") Long projectId,
                                                @RequestParam("fileImage") MultipartFile[] multipartFile) {
        ResponseData<Project> responseData = new ResponseData<>();

        if (multipartFile.length == 0) {
            responseData.setStatus(false);
            responseData.setMessages(Collections.singletonList("Please Select a Image"));
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(projectService.uploadProjectImage(projectId, multipartFile));
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

    @GetMapping("/project/findByTag/{tagId}")
    public ResponseEntity<List<Project>> findProjectByTag(@PathVariable("tagId") Long tagId) {
        List<Project> projects = projectService.findProjectByTag(tagId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/project/findByTech/{techId}")
    public ResponseEntity<List<Project>> findProjectByTech(@PathVariable("techId") Long techId) {
        List<Project> projects = projectService.findProjectByTech(techId);
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/project")
    public ResponseEntity<ResponseData<Project>> updateProject(@Valid @RequestBody ProjectDTO projectDTO,
                                                               Errors errors) {
        ResponseData<Project> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Project project = modelMapper.map(projectDTO, Project.class);
        responseData.setStatus(true);
        responseData.setPayload(projectService.updateProject(project));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{tagId}/project/{projectId}")
    public ResponseEntity<ResponseData<Project>> saveTagToProject(@PathVariable("tagId") Long tagId,
                                                                  @PathVariable("projectId") Long projectId) {
        ResponseData<Project> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setPayload(projectService.addTagToProject(tagId, projectId));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{techId}/add-tech-to-project/{projectId}")
    public ResponseEntity<Project> saveTechToProject(@PathVariable("techId") Long techId,
                                                     @PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(projectService.addTechToProject(techId, projectId));
    }

    @PutMapping("/{techId}/remove-tech-from-project/{projectId}")
    public ResponseEntity<String> removeTechFromProject(@PathVariable("techId") Long techId,
                                                     @PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(projectService.removeTechFromProject(techId, projectId));
    }

    @DeleteMapping("/project/{id}")
    public void removeProject(@PathVariable("id") Long projectId) {
        projectService.deleteProject(projectId);
    }
}
