package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tech;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TechRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TechServiceImpl implements TechService{

    private final TechRepository techRepository;
    private final ProjectRepository projectRepository;
    private final EntityManager entityManager;

    @Override
    public Tech saveTech(Tech tech) {
        return techRepository.save(tech);
    }

    @Override
    public List<Tech> getAllTechByProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        assert project != null;
        return project.getTechList();
    }

    @Override
    public List<Tech> getAllTech() {
        return techRepository.findAll();
    }

    @Override
    public List<Tech> saveBatch(Iterable<Tech> techList) {
        return techRepository.saveAll(techList);
    }

    @Override
    @Cacheable(value = "Tech", key = "#techId")
    public Tech getTechById(Long techId) {
        Optional<Tech> tech = techRepository.findById(techId);
        if (tech.isEmpty()) {
            return null;
        }
        return tech.get();
    }

    @Override
    @Cacheable(value = "Tech", key = "#name")
    public List<Tech> getTechByName(String name) {
        return techRepository.findByNameContains(name);
    }

    @Override
    @CacheEvict(value = "Tech", key = "#techId")
    public String deleteTech(Long techId) {
        Tech tech = techRepository.findById(techId).orElse(null);

        assert tech != null;
        List<Project> projects = tech.getProjects();

        for (Project project : projects) {
            project.getTechList().remove(tech);
            entityManager.merge(project);
        }

        techRepository.deleteById(techId);
        return "Tech is Removed";
    }
}
