package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Skill;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SkillServiceImpl implements SkillService{

    private final SkillRepository skillRepository;
    private final EntityManager entityManager;

    @Override
    public Skill saveSkill(Skill skills) {
        return skillRepository.save(skills);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Long skillId) {
        return skillRepository.findById(skillId).orElse(null);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        Skill skillUpdate = skillRepository.findById(skill.getSkillId()).orElse(null);

        assert skillUpdate != null;
        skillUpdate.setImageUrl(skill.getImageUrl());
        skillUpdate.setName(skill.getName());

        return skillRepository.save(skillUpdate);
    }

    @Override
    public String removeSkill(Long skillId) {
        Skill skill = skillRepository.findById(skillId).orElse(null);

        assert skill != null;
        List<User> users = skill.getUsers();

        for (User user : users) {
            user.getSkills().remove(skill);
            entityManager.merge(user);
        }

        skillRepository.deleteById(skillId);
        return "Skill is removed";
    }
}
