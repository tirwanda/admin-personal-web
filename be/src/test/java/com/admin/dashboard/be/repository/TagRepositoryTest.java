package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TagRepositoryTest {

    @Autowired
    TagRepository tagRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(tagRepository).isNotNull();
    }

    @Test
    void testSaveTag() {
        Tag tag = Tag.builder().name("Mobile").build();
        Tag result = tagRepository.save(tag);
        assertThat(result).isNotNull();
    }

    @Test
    void testFindTagByName() {
        Tag tag = Tag.builder().name("Web Application").build();
        tagRepository.save(tag);
        Tag result = tagRepository.findTagByName("Web Application");
        assertThat(result).isEqualTo(tag);
    }
}