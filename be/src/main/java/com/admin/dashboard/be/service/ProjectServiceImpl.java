package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.ProjectRepository;
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

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void saveProjectToUser(String projectTitle, String username) {
        User user = userRepository.findByUsername(username);
        Project project = projectRepository.findProjectByTitle(projectTitle);

        user.getProjects().add(project);
    }

    @Override
    public void deleteProject(String projectTitle) {
        Project project = projectRepository.findProjectByTitle(projectTitle);
        projectRepository.deleteById(project.getProjectId());
    }

    @Override
    public List<Project> getProjectsByUser(String username) {
        User user = userRepository.findByUsername(username);
        return user.getProjects();
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProject(String projectTitle) {
        return projectRepository.findProjectByTitle(projectTitle);
    }
}
