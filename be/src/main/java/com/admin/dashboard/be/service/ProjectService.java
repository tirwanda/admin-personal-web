package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    void deleteProject(Long projectId);
    List<Project> getProjectsByUser(String username);
    List<Project> getAllProjects();
    Project getProject(Long projectId);
}
