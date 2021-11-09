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
import java.util.Optional;

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
    public User addProjectToUser(Long userId, Long projectId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Project> project = projectRepository.findById(projectId);

        if (user.isEmpty() || project.isEmpty()) {
            return null;
        }
        user.get().getProjects().add(project.get());
        return user.get();
    }

    @Override
    public Project addTagToProject(Long tagId, Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        Optional<Tag> tag = tagRepository.findById(tagId);

        if (project.isEmpty() || tag.isEmpty()) {
            return null;
        }
        project.get().setTag(tag.get());
        return project.get();
    }

    @Override
    public List<Project> getProjectsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }
        return user.get().getProjects();
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<Project> findProjectByTag(Long tagId) {
        return projectRepository.findProjectByTag(tagId);
    }

    @Override
    public Project getProject(Long projectId) {
        return projectRepository.findProjectByProjectId(projectId);
    }
}
