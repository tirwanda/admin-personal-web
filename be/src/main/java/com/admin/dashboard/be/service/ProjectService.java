package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.User;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    void deleteProject(Long projectId);
    User addProjectToUser(Long userId, Long projectId);
    Project addTagToProject(Long tagId, Long projectId);
    List<Project> getProjectsByUserId(Long userId);
    List<Project> getAllProjects();
    Project getProject(Long projectId);
}
