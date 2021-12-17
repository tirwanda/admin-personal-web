package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Tech;

import java.util.List;
import java.util.Set;

public interface TechService {
    Tech saveTech(Tech tech);
    Set<Tech> getAllTechByProject(Long projectId);
    List<Tech> getAllTech();
    List<Tech> saveBatch(Iterable<Tech> techList);
    Tech getTechById(Long techId);
    List<Tech> getTechByName(String name);
    String deleteTech(Long techId);
}
