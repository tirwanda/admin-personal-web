package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class TestTagRepository {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Tag tag1;
    private Tag tag2;

    @BeforeEach
    public void init() {
        tag1 = Tag.builder().name("Web App").build();
        entityManager.persist(tag1);

        tag2 = Tag.builder().name("Mobile App").build();
        entityManager.persist(tag2);
    }

    @Test
    public void itShouldSaveTag() {
        Tag tag = Tag.builder().name("Mobile").build();
        Tag tagSave = tagRepository.save(tag);

        Assertions.assertTrue(tagSave.getTagId() > 0);
        Assertions.assertEquals(tag.getName(), tagSave.getName());
    }

    @Test
    public void itShouldReturnFalseIfTagIsEmpty() {
        Assertions.assertFalse(tagRepository.findAll().isEmpty());
    }

    @Test
    public void itShouldReturnTagWhenFindById() {
        Long tagId = tag1.getTagId();
        Tag actualTag = tagRepository.findById(tagId).orElse(null);
        assert actualTag != null;
        Assertions.assertEquals(tagId, actualTag.getTagId());
    }

    @Test
    public void itShouldReturnListOfTag() {
        List<Tag> tagList = tagRepository.findAll();
        Assertions.assertTrue(tagList.containsAll(List.of(tag1, tag2)));
    }

    @Test
    public void itShouldUpdatingTag() {
        Long tagId = tag1.getTagId();
        Tag tagWillUpdate = tagRepository.findById(tagId).orElse(null);
        assert tagWillUpdate != null;
        tagWillUpdate.setName("Tag Updated");
        Tag actualTag = tagRepository.save(tagWillUpdate);

        Assertions.assertEquals(tagWillUpdate, actualTag);
    }

    @Test
    public void itShouldDeleteTagById() {
        Long tagId = tag1.getTagId();
        tagRepository.deleteById(tagId);

        Assertions.assertFalse(tagRepository.findAll().contains(tag1));
    }

    @Test
    public void itShouldDeleteAllTags() {
        tagRepository.deleteAll();
        Assertions.assertTrue(tagRepository.findAll().isEmpty());
    }
}
