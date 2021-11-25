package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(tagRepository).isNotNull();
    }

    @Test
    void testSaveTag() {
        Tag tag = new Tag(1L, "java", new ArrayList<>());
        assertThat(tagRepository.save(tag)).isNotNull();
    }
}