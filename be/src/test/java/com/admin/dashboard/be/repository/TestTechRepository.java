package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Tech;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class TestTechRepository {

    @Autowired
    private TechRepository techRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Tech tech1;
    private Tech tech2;
    private Long techId;

    @BeforeEach
    public void init() {
        tech1 = Tech.builder().name("Java").build();
        entityManager.persist(tech1);

        tech2 = Tech.builder().name("React Js").build();
        entityManager.persist(tech2);

        techId = tech1.getTechId();
    }

    @Test
    public void itShouldSaveTech() {
        Tech tech = Tech.builder().name("MySql").build();
        Tech techSave = techRepository.save(tech);

        Assertions.assertTrue(techSave.getTechId() > 0);
        Assertions.assertEquals(tech.getName(), techSave.getName());
    }

    @Test
    public void itShouldReturnFalseIfTechIsEmpty() {
        Assertions.assertFalse(techRepository.findAll().isEmpty());
    }

    @Test
    public void itShouldReturnTechWhenFindById() {
        Tech actualTech = techRepository.findById(techId).orElse(null);
        assert actualTech != null;
        Assertions.assertTrue(actualTech.getTechId() > 0);
    }

    @Test
    public void itShouldReturnListOfTech() {
        List<Tech> techList = techRepository.findAll();
        Assertions.assertTrue(techList.containsAll(List.of(tech1, tech2)));
    }

    @Test
    public void itShouldUpdatingTech() {
        Tech techWillUpdated = techRepository.findById(techId).orElse(null);
        assert techWillUpdated != null;
        techWillUpdated.setName("Updated Tech");
        Tech actualTech = techRepository.save(techWillUpdated);

        Assertions.assertEquals("Updated Tech", actualTech.getName());
    }

    @Test
    public void itShouldDeleteTechById() {
        techRepository.deleteById(techId);
        Assertions.assertFalse(techRepository.findAll().contains(tech1));
    }

    @Test
    public void itShouldDeleteAllTech() {
        techRepository.deleteAll();
        Assertions.assertTrue(techRepository.findAll().isEmpty());
    }
}
