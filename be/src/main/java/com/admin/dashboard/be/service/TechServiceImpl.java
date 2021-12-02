package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tech;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TechRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TechServiceImpl implements TechService{

    private final TechRepository techRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Tech saveTech(Tech tech) {
        return techRepository.save(tech);
    }

    @Override
    public List<Tech> getAllTechByProject(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            return null;
        }
        return project.get().getTechList();
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
    public Tech getTechById(Long techId) {
        Optional<Tech> tech = techRepository.findById(techId);
        if (tech.isEmpty()) {
            return null;
        }
        return tech.get();
    }

    @Override
    public List<Tech> getTechByName(String name) {
        return techRepository.findByNameContains(name);
    }

    @Override
    public void deleteTech(Long techId) {
        techRepository.deleteById(techId);
    }
}
