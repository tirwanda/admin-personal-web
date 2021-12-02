package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Tech;

import java.util.List;

public interface TechService {
    Tech saveTech(Tech tech);
    List<Tech> getAllTechByProject(Long projectId);
    List<Tech> getAllTech();
    List<Tech> saveBatch(Iterable<Tech> techList);
    Tech getTechById(Long techId);
    List<Tech> getTechByName(String name);
    void deleteTech(Long techId);
}
