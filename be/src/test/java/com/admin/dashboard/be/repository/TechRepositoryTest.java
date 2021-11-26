package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Tech;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TechRepositoryTest {

    @Autowired
    private TechRepository techRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(techRepository).isNotNull();
        assertThat(projectRepository).isNotNull();
    }

    @Test
    void testSaveTech() {
        Tech tech = Tech.builder().name("Node").build();
        techRepository.save(tech);

        Assertions.assertThat(tech.getTechId()).isGreaterThan(0);
    }
}