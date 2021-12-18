package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tag;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Cacheable(value = "Tag", key = "#projectId")
    public Tag getTagByProject(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            return null;
        }
        return project.get().getTag();
    }

    @Override
    @Cacheable(value = "Tag", key = "#tagId")
    public Tag getTag(Long tagId) {
        Optional<Tag> tag = tagRepository.findById(tagId);
        return tag.orElse(null);
    }

    @Override
    @Cacheable("Tags")
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    @CacheEvict(value = "Tag", key = "#tagId")
    public String deleteTag(Long tagId) {
        Optional<Tag> tag = tagRepository.findById(tagId);
        List<Project> projects = tag.orElseThrow().getProjects();
        for (Project project : projects) {
            project.removeTag();
        }
        tagRepository.deleteById(tagId);
        return "Tag is Removed";
    }

    @Override
    public String addProjectToTag(Long tagId, Long projectId) {
        Tag tag = tagRepository.findById(tagId).orElse(null);
        Project project = projectRepository.findById(projectId).orElse(null);

        assert tag != null;
        tag.addProjectToTag(project);
        return "Success added Project to Tag";
    }

    @Override
    public String removeProjectFromTag(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        assert project != null;
        Tag tag = project.getTag();

        tag.removeProjectFromTag(project);
        return "Success Deleting Project from Tag";
    }
}
