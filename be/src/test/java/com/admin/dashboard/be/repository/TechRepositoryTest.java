package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Tech;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
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
}