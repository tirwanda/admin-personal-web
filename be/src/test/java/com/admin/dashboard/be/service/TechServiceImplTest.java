package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tech;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TechRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class TechServiceImplTest {
    @Mock private TechRepository techRepository;
    @Mock private ProjectRepository projectRepository;
    @Mock private ProjectServiceImpl projectService;

    @InjectMocks
    private TechServiceImpl techService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        techService = new TechServiceImpl(techRepository, projectRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(techRepository).isNotNull();
        assertThat(projectRepository).isNotNull();
        assertThat(projectService).isNotNull();
    }

    @Test
    void getAllTechByProject() {
        Tech tech = Tech.builder()
                .name("Spring Boot")
                .build();
        Tech tech2 = Tech.builder()
                .name("React Js")
                .build();

        Project project = Project.builder()
                .projectId(1L)
                .title("Project1")
                .descriptions("This is example of project")
                .techList(Arrays.asList(tech, tech2))
                .build();
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(project));
        List<Tech> actualTechList = techService.getAllTechByProject(1L);
        assert project != null;
        assertThat(actualTechList).isEqualTo(project.getTechList());
    }

    @Test
    void getAllTech() {
        Tech tech = Tech.builder()
                .name("Spring Boot")
                .build();
        Tech tech2 = Tech.builder()
                .name("React Js")
                .build();
        
        Mockito.when(techRepository.findAll()).thenReturn(Arrays.asList(tech, tech2));
        List<Tech> actualTechList = techService.getAllTech();
        assertThat(actualTechList).isEqualTo(Arrays.asList(tech, tech2));
    }

    @Test
    void getTechById() {
        Tech tech = Tech.builder()
                .techId(1L)
                .name("Spring Boot")
                .build();

        Mockito.when(techRepository.findById(1L)).thenReturn(Optional.ofNullable(tech));
        Tech actualTech = techService.getTechById(1L);
        assertThat(actualTech).isEqualTo(tech);
    }

    @Test
    void getTechByName() {
        Tech tech = Tech.builder()
                .techId(1L)
                .name("Spring Boot")
                .build();

        Mockito.when(techRepository.findByNameContains("Spring Boot")).thenReturn(Collections.singletonList(tech));
        List<Tech> actualTech = techService.getTechByName("Spring Boot");
        assertThat(actualTech).isEqualTo(Collections.singletonList(tech));
    }
}