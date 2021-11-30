package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Tech;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TechRepositoryTest {

    @Autowired
    private TechRepository techRepository;

    @AfterEach
    void tearDown() {
        techRepository.deleteAll();
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(techRepository).isNotNull();
    }

    @Test
    void testSaveTech() {
        Tech tech = Tech.builder().name("Node").build();
        techRepository.save(tech);

        Assertions.assertThat(tech.getTechId()).isGreaterThan(0);
    }

    @Test
    void TestFindByNameContains() {
        Tech tech = Tech.builder().name("Material UI").build();
        techRepository.save(tech);
        List<Tech> resultTech = techRepository.findByNameContains("Material");
        assertThat(tech).isIn(resultTech);
    }
}