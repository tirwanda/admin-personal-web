package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tech;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.TechRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;

import javax.persistence.EntityManager;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
class TechServiceImplTest {
    @Mock private TechRepository techRepository;
    @Mock private ProjectRepository projectRepository;
    @Mock private ProjectServiceImpl projectService;

    private EntityManager entityManager;

    @InjectMocks
    private TechServiceImpl techService;
    private AutoCloseable autoCloseable;

    private Tech tech1;
    private Tech tech2;
    private Project project;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        techService = new TechServiceImpl(techRepository, projectRepository, entityManager);

        tech1 = Tech.builder()
                .name("Spring Boot")
                .techId(1L)
                .build();
        tech2 = Tech.builder()
                .techId(2L)
                .name("React Js")
                .build();

        project = Project.builder()
                .projectId(1L)
                .title("Project1")
                .descriptions("This is example of project")
                .techList(Arrays.asList(tech1, tech2))
                .build();
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
    void itShouldSaveTech() {
        Mockito.when(techRepository.save(tech1)).thenReturn(tech1);
        Tech actualTech = techService.saveTech(tech1);

        assertThat(actualTech).isEqualTo(tech1);
    }

    @Test
    void itShouldReturnListOfTechWhenFindTechByProjectId() {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(project));
        List<Tech> actualTechList = techService.getAllTechByProject(1L);
        assert project != null;
        assertThat(actualTechList).isEqualTo(project.getTechList());
    }

    @Test
    void itShouldReturnAllTech() {
        Mockito.when(techRepository.findAll()).thenReturn(Arrays.asList(tech1, tech2));
        List<Tech> actualTechList = techService.getAllTech();
        assertThat(actualTechList).isEqualTo(Arrays.asList(tech1, tech2));
    }

    @Test
    void itShouldSaveBatchTech() {
        Mockito.when(techRepository.saveAll(List.of(tech1, tech2))).thenReturn(List.of(tech1, tech2));
        assertThat(techRepository.saveAll(List.of(tech1, tech2))).isEqualTo(List.of(tech1, tech2));
    }

    @Test
    void itShouldReturnTechWhenFindById() {
        Mockito.when(techRepository.findById(1L)).thenReturn(Optional.ofNullable(tech1));
        Tech actualTech = techService.getTechById(1L);
        assertThat(actualTech).isEqualTo(tech1);
    }

    @Test
    void itShouldReturnTechWhenFindByNameContains() {
        Mockito.when(techRepository.findByNameContains("Spring Boot")).thenReturn(Collections.singletonList(tech1));
        List<Tech> actualTech = techService.getTechByName("Spring Boot");
        assertThat(actualTech).isEqualTo(Collections.singletonList(tech1));
    }

    @Test
    void itShouldDeleteTech() {
        Mockito.when(techRepository.findById(1L)).thenReturn(Optional.of(tech1));
        techService.deleteTech(tech1.getTechId());
        verify(techRepository).deleteById(tech1.getTechId());
    }

    @Test
    void itShouldUpdateTech() {
        tech1.setName("Tech Update");
        Mockito.when(techRepository.findById(tech1.getTechId())).thenReturn(Optional.of(tech1));
        Mockito.when(techRepository.save(tech1)).thenReturn(tech1);
        Tech actualTech = techService.updateTech(tech1);
        assertThat(actualTech).isEqualTo(tech1);
    }
}