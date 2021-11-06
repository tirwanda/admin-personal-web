package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tag;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TagRepository;
import com.admin.dashboard.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService{
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public void addProjectToUser(Long userId, Long projectId) {
        User user = userRepository.findById(userId).get();
        Project project = projectRepository.findById(projectId).get();
        user.getProjects().add(project);
    }

    @Override
    public Project addTagToProject(Long tagId, Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        Tag tag = tagRepository.findById(tagId).get();
        project.setTag(tag);
        return project;
    }

    @Override
    public List<Project> getProjectsByUserId(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getProjects();
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProject(Long projectId) {
        return projectRepository.findProjectByProjectId(projectId);
    }
}
