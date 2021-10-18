package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tech;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TechRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

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
    public List<Tech> GetAllTechByProject(Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        return project.getTechList();
    }

    @Override
    public List<Tech> getAllTech() {
        return techRepository.findAll();
    }

    @Override
    public Tech getTechById(Long techId) {
        return techRepository.findById(techId).get();
    }

    @Override
    public void deleteTech(Long techId) {
        techRepository.deleteById(techId);
    }
}
