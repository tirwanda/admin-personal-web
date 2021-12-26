package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.ProjectImage;
import com.admin.dashboard.be.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    void deleteProject(Long projectId);
    User addProjectToUser(Long userId, Long projectId);
    Project addTagToProject(Long tagId, Long projectId);
    Project addTechToProject(Long techId, Long projectId);
    Iterable<Project> getProjectByTitleContains(String title, Pageable pageable);
    List<Project> getProjectsByUserId(Long userId);
    List<Project> getAllProjects();
    List<Project> findProjectByTag(Long tagId);
    List<Project> findProjectByTech(Long techId);
    Project getProject(Long projectId);
    String removeTechFromProject(Long techId, Long projectId);
    Project updateProject(Project project);
    Project uploadProjectImage(Long projectId, MultipartFile[] files);
}
