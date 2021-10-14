package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    void saveProjectToUser(String projectTitle, String username);
    void deleteProject(String projectName);
    List<Project> getProjectsByUser(String username);
    List<Project> getAllProjects();
    Project getProject(String projectName);
}
