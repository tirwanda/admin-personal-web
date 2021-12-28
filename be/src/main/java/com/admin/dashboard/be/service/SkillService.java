package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    Skill saveSkill(Skill skills);
    List<Skill> getAllSkills();
    Skill getSkillById(Long skillId);
    Skill updateSkill(Skill skill);
    String removeSkill(Long skillId);
}
