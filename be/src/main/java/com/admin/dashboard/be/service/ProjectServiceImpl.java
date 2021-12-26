package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tag;
import com.admin.dashboard.be.entity.Tech;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TagRepository;
import com.admin.dashboard.be.repository.TechRepository;
import com.admin.dashboard.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService{
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;
    private final TechRepository techRepository;

    private final TechService techService;
    private final ProjectImageService projectImageService;

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
    public Project addTechToProject(Long techId, Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Tech tech = techRepository.findById(techId).orElse(null);

        assert project != null;
        project.addTech(tech);
        return project;
    }

    @Override
    public String removeTechFromProject(Long techId, Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Tech tech = techRepository.findById(techId).orElse(null);

        assert project != null;
        project.removeTech(tech);

        return "Success remove Teh from Project";
    }

    @Override
    public Project updateProject(Project project) {
        Project projectUpdate = projectRepository.findById(project.getProjectId()).orElse(null);

        assert projectUpdate != null;
        projectUpdate.setDescriptions(project.getDescriptions());
        projectUpdate.setTitle(project.getTitle());
        projectUpdate.setDemo(project.getDemo());
        projectUpdate.setGithub(project.getGithub());
        projectRepository.save(projectUpdate);
        return projectUpdate;
    }

    @Override
    public Project uploadProjectImage(Long projectId, MultipartFile[] files) {
        Project project = projectRepository.findById(projectId).orElse(null);

        assert project != null;
        for (MultipartFile file : files) {
            project.getProjectImageList().add(projectImageService.saveProjectImage(file));
        }

        return project;
    }

    @Override
    @Cacheable(value = "Project", key = "#title")
    public Iterable<Project> getProjectByTitleContains(String title, Pageable pageable) {
        return projectRepository.findProjectByTitleContains(title, pageable);
    }

    @Override
    @Cacheable(value = "Project", key = "#userId")
    public List<Project> getProjectsByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }
        return user.get().getProjects();
    }

    @Override
    @Cacheable("Projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    @Cacheable(value = "Project", key = "#tagId")
    public List<Project> findProjectByTag(Long tagId) {
        return projectRepository.findProjectByTag(tagId);
    }

    @Override
    @Cacheable(value = "Project", key = "#techId")
    public List<Project> findProjectByTech(Long techId) {
        Tech tech = techService.getTechById(techId);
        if (tech == null) {
            return new ArrayList<>();
        }
        return projectRepository.findProjectByTech(tech);
    }

    @Override
    @Cacheable(value = "Project", key = "#projectId")
    public Project getProject(Long projectId) {
        return projectRepository.findProjectByProjectId(projectId);
    }
}
