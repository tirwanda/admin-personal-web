package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Skill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class TestSkillRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SkillRepository skillRepository;

    private Skill skill1;
    private Skill skill2;
    private Skill skill3;

    @BeforeEach
    public void init() {
        skill1 = Skill.builder().name("Rest API").build();
        entityManager.persist(skill1);

        skill2 = Skill.builder().name("Unit Testing").build();
        entityManager.persist(skill2);

        skill3 = Skill.builder().name("TDD").build();
        entityManager.persist(skill3);
    }

    @Test
    public void itShouldCreateSkill() {
        Skill skill = Skill.builder().name("AGILE").build();
        Skill skillSave = skillRepository.save(skill);
        Assertions.assertTrue(skillSave.getSkillId() > 0);
        Assertions.assertEquals("AGILE", skillSave.getName());
    }

    @Test
    public void itShouldReturnFalseIfSkillIsEmpty() {
        List<Skill> skills = skillRepository.findAll();
        Assertions.assertFalse(skills.isEmpty());
    }

    @Test
    public void itShouldReturnListOfSkill() {
        List<Skill> skills = skillRepository.findAll();
        Assertions.assertTrue(skills.containsAll(List.of(skill1, skill2, skill3)));
    }

    @Test
    public void itShouldReturnSkillWhenFindById(){
        Long skillId = skill1.getSkillId();
        Skill skill = skillRepository.findById(skillId).orElse(null);
        assert skill != null;
        Assertions.assertEquals(skillId, skill.getSkillId());
    }

    @Test
    public void itShouldUpdatedSkill() {
        Long skillId = skill1.getSkillId();
        Skill skill = skillRepository.findById(skillId).orElse(null);

        assert skill != null;
        skill.setName("New Skill");

        Skill actualSkill = skillRepository.save(skill);
        Assertions.assertEquals("New Skill", actualSkill.getName());
    }

    @Test
    public void itShouldDeletedSkillById() {
        Long skillId = skill1.getSkillId();
        skillRepository.deleteById(skillId);

        Assertions.assertFalse(skillRepository.findAll().contains(skill1));
    }

    @Test
    public void itShouldDeleteAllSkill() {
        skillRepository.deleteAll();
        Assertions.assertTrue(skillRepository.findAll().isEmpty());
    }
}
