package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tag;
import com.admin.dashboard.be.entity.Tech;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TagRepository;
import com.admin.dashboard.be.repository.TechRepository;
import com.admin.dashboard.be.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
class ProjectServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private TechRepository techRepository;
    @Mock private ProjectRepository projectRepository;
    @Mock private TagRepository tagRepository;

    @Mock private ProjectImageService projectImageService;
    @Mock private TagService tagService;

    @InjectMocks
    private ProjectServiceImpl projectService;
    private AutoCloseable autoCloseable;

    private Project project1;
    private Project project2;
    private Tech tech1;
    private Tag tag1;
    private User user1;
    private User user2;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        project1 = Project.builder()
                .title("Project 1")
                .descriptions("Project 1 description")
                .techList(new ArrayList<>())
                .tag(new Tag())
                .github("www.github.com")
                .demo("www.google.com")
                .build();

        project2 = Project.builder()
                .title("Project 2")
                .descriptions("Project 2 description")
                .techList(new ArrayList<>())
                .tag(new Tag())
                .github("www.github.com")
                .demo("www.google.com")
                .build();

        tag1 = Tag.builder()
                .name("Tag 1")
                .projects(new ArrayList<>())
                .build();

        tech1 = Tech.builder()
                .name("Spring Boot")
                .techId(1L)
                .projects(new ArrayList<>())
                .build();

        user1 = User.builder()
                .username("user1")
                .name("user satu")
                .password("12345")
                .title("Backend Engineer")
                .roles(new ArrayList<>())
                .projects(new ArrayList<>())
                .skills(new ArrayList<>())
                .email("usersatu@gmail.com")
                .build();

        user2 = User.builder()
                .username("user2")
                .name("user dua")
                .password("12345")
                .title("Backend Engineer")
                .roles(new ArrayList<>())
                .projects(List.of(project2))
                .skills(new ArrayList<>())
                .email("userdua@gmail.com")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void injectedMockAreNotNull() {
        assertThat(userRepository).isNotNull();
        assertThat(techRepository).isNotNull();
        assertThat(projectRepository).isNotNull();
        assertThat(projectImageService).isNotNull();
        assertThat(tagRepository).isNotNull();
        assertThat(tagService).isNotNull();
        assertThat(projectService).isNotNull();
    }

    @Test
    void itShouldSaveProject() {
        Mockito.when(projectRepository.save(project1)).thenReturn(project1);
        Project savedProject = projectService.saveProject(project1);

        verify(projectRepository, times(1)).save(project1);
        assertThat(savedProject).isEqualTo(project1);
    }

    @Test
    void itShouldDeleteProject() {
        projectService.deleteProject(project1.getProjectId());
        verify(projectRepository, times(1)).deleteById(project1.getProjectId());
        assertThat(project1.getProjectId()).isNull();
    }

    @Test
    void itShouldAddProjectToUser() {
        Mockito.when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        Mockito.when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
        User actualUser = projectService.addProjectToUser(user1.getUserId(), project1.getProjectId());

        verify(userRepository, times(1)).findById(user1.getUserId());
        verify(projectRepository, times(1)).findById(project1.getProjectId());
        assertThat(actualUser.getProjects().size()).isEqualTo(1);
        Assertions.assertTrue(actualUser.getProjects().contains(project1));
    }

    @Test
    void itShouldAddTagToProject() {
        when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
        when(tagRepository.findById(tag1.getTagId())).thenReturn(Optional.of(tag1));
        Project actualProject = projectService.addTagToProject(tag1.getTagId(), project1.getProjectId());

        verify(projectRepository, times(1)).findById(project1.getProjectId());
        verify(tagRepository, times(1)).findById(tag1.getTagId());
        assertThat(actualProject.getTag()).isEqualTo(tag1);
    }

    @Test
    void itShouldAddTechToUser() {
        Mockito.when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
        Mockito.when(techRepository.findById(tech1.getTechId())).thenReturn(Optional.of(tech1));
        Project actualProject = projectService.addTechToProject(tech1.getTechId(), project1.getProjectId());

        verify(projectRepository, times(1)).findById(project1.getProjectId());
        verify(techRepository, times(1)).findById(tech1.getTechId());
        assertThat(actualProject.getTechList().size()).isEqualTo(1);
        Assertions.assertTrue(actualProject.getTechList().contains(tech1));
    }

    @Test
    void itShouldReturnListProjectByUserId() {
        Mockito.when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user2));
        List<Project> actualProjects = projectService.getProjectsByUserId(user2.getUserId());

        verify(userRepository, times(1)).findById(user2.getUserId());
        assertThat(actualProjects.size()).isEqualTo(1);
        Assertions.assertTrue(actualProjects.contains(project2));
    }
}
