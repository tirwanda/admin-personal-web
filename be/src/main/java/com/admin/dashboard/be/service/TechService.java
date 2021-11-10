package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Tech;

import java.util.List;

public interface TechService {
    Tech saveTech(Tech tech);
    List<Tech> GetAllTechByProject(Long projectId);
    List<Tech> getAllTech();
    Tech getTechById(Long techId);
    Tech getTechByName(String name);
    void deleteTech(Long techId);
}
