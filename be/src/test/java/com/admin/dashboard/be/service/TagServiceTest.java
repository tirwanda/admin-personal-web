package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tag;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TagRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TagServiceTest {

    @Mock TagRepository tagRepository;
    @Mock ProjectRepository projectRepository;

    @InjectMocks
    private TagServiceImpl tagService;
    private AutoCloseable autoCloseable;

    private Tag tag1;
    private Project project1;

    @BeforeEach
    void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        tag1 = Tag.builder()
                .name("React")
                .projects(new ArrayList<>())
                .build();

        project1 = Project.builder()
                .title("Project 1")
                .descriptions("Project 1 description")
                .techList(new ArrayList<>())
                .tag(new Tag())
                .github("www.github.com")
                .demo("www.google.com")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void injectedMockAreNotNull() {
        assertThat(tagRepository).isNotNull();
        assertThat(projectRepository).isNotNull();
    }

    @Test
    void itShouldSaveTag() {
        Mockito.when(tagRepository.save(tag1)).thenReturn(tag1);
        Tag actualTag = tagService.saveTag(tag1);

        verify(tagRepository, times(1)).save(tag1);
        assertThat(actualTag.getName()).isEqualTo(tag1.getName());
    }

    @Test
    void itShouldReturnTagByProject() {
        tag1.addProjectToTag(project1);
        Mockito.when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
        Tag actualTag = tagService.getTagByProject(project1.getProjectId());

        verify(projectRepository, times(1)).findById(project1.getProjectId());
        assertThat(actualTag).isEqualTo(tag1);
    }

    @Test
    void itShouldReturnTagById() {
        Mockito.when(tagRepository.findById(tag1.getTagId())).thenReturn(Optional.of(tag1));
        Tag actualTag = tagService.getTag(tag1.getTagId());

        verify(tagRepository, times(1)).findById(tag1.getTagId());
        assertThat(actualTag).isEqualTo(tag1);
    }

    @Test
    void itShouldReturnListOfTag() {
        Mockito.when(tagRepository.findAll()).thenReturn(List.of(tag1));
        List<Tag> actualTags = tagService.getAllTags();

        verify(tagRepository, times(1)).findAll();
        assertThat(actualTags.contains(tag1)).isTrue();
    }

    @Test
    void itShouldDeleteTag() {
        tag1.addProjectToTag(project1);
        Mockito.when(tagRepository.findById(tag1.getTagId())).thenReturn(Optional.of(tag1));
        String response = tagService.deleteTag(tag1.getTagId());

        verify(tagRepository, times(1)).findById(tag1.getTagId());
        assertThat(response).isEqualTo("Tag is Removed");
    }

    @Test
    void itShouldAddProjectToTag() {
        Mockito.when(tagRepository.findById(tag1.getTagId())).thenReturn(Optional.of(tag1));
        Mockito.when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
        String response = tagService.addProjectToTag(tag1.getTagId(), project1.getProjectId());

        verify(tagRepository, times(1)).findById(tag1.getTagId());
        verify(projectRepository, times(1)).findById(project1.getProjectId());
        assertThat(response).isEqualTo("Success added Project to Tag");
    }

    @Test
    void itShouldRemoveProjectFromTag() {
        tag1.addProjectToTag(project1);
        Mockito.when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
        String response = tagService.removeProjectFromTag(project1.getProjectId());

        verify(projectRepository, times(1)).findById(project1.getProjectId());
        assertThat(response).isEqualTo("Success Deleting Project from Tag");
    }

    @Test
    void itShouldUpdatingTag() {
        Mockito.when(tagRepository.findById(tag1.getTagId())).thenReturn(Optional.of(tag1));
        Mockito.when(tagRepository.save(tag1)).thenReturn(tag1);

        tag1.setName("Update");
        Tag actualTag = tagService.updateTag(tag1);
        verify(tagRepository, times(1)).findById(tag1.getTagId());
        verify(tagRepository, times(1)).save(tag1);

        assertThat(actualTag.getName()).isEqualTo("Update");
    }
}
