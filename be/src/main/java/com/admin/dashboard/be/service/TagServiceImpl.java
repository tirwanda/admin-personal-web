package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tag;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTagByProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return project.getTag();
    }

    @Override
    public Tag getTag(Long tagId) {
        Optional<Tag> tag = tagRepository.findById(tagId);
        return tag.orElse(null);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);
    }
}
