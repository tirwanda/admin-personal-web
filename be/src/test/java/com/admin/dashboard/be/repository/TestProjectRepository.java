package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tech;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class TestProjectRepository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepository;

    private Project project1;
    private Project project2;
    private Project project3;

    @BeforeEach
    public void init() {
        project1 = Project.builder()
                .title("Spring boot project")
                .descriptions("This is only for testing")
                .github("www.github.com")
                .demo("www.google.com")
                .build();
        entityManager.persist(project1);

        project2 = Project.builder()
                .title("React Js")
                .descriptions("This is only for testing")
                .github("www.github.com")
                .demo("www.google.com")
                .build();
        entityManager.persist(project2);

        project3 = Project.builder()
                .title("React Native")
                .descriptions("This is only for testing")
                .github("www.github.com")
                .demo("www.google.com")
                .build();
        entityManager.persist(project3);
    }

    @Test
    public void itShouldSaveProject() {

        Tech tech = Tech.builder().name("Spring Boot").build();

        Project project = Project.builder()
                .title("Spring boot project")
                .descriptions("This is only for testing")
                .github("www.github.com")
                .techList(List.of(tech))
                .demo("www.google.com")
                .build();

        Project projectSave = projectRepository.save(project);

        Assertions.assertNotNull(projectSave);
        Assertions.assertTrue(projectSave.getProjectId() > 0);
        Assertions.assertEquals("Spring boot project", projectSave.getTitle());
        Assertions.assertEquals("This is only for testing", projectSave.getDescriptions());
        Assertions.assertEquals("www.github.com", projectSave.getGithub());
        Assertions.assertEquals("www.google.com", projectSave.getDemo());
        Assertions.assertEquals(List.of(tech), projectSave.getTechList());
    }

    @Test
    public void itShouldFReturnFalseIfRepositoryIsEmpty() {
        List<Project> projects = projectRepository.findAll();
        Assertions.assertFalse(projects.isEmpty());
    }

    @Test
    public void itShouldFindAllProject() {
        List<Project> projectList = projectRepository.findAll();
        Assertions.assertEquals(3, projectList.size());
        Assertions.assertTrue(projectList.containsAll(List.of(project1, project2, project3)));
    }

    @Test
    public void itShouldFindProjectById() {
        Long projectId = project1.getProjectId();
        Assertions.assertEquals(project1, projectRepository.findProjectByProjectId(projectId));
    }

    @Test
    public void itShouldDeleteProjectById() {
        Long projectId = project1.getProjectId();
        projectRepository.deleteById(projectId);
        List<Project> projectList = projectRepository.findAll();

        Assertions.assertFalse(projectList.contains(project1));
    }

    @Test
    public void itShouldUpdatingProject() {
        Long projectId = project1.getProjectId();
        Project projectWillUpdated = projectRepository.findProjectByProjectId(projectId);
        projectWillUpdated.setTitle("Title is updated");
        projectWillUpdated.setDescriptions("Description is updated");
        projectWillUpdated.setGithub("Github is updated");
        projectWillUpdated.setDemo("Demo is updated");

        Project projectUpdated = projectRepository.save(projectWillUpdated);
        Assertions.assertEquals(projectUpdated, projectRepository.findProjectByProjectId(projectId));
    }

    @Test
    public void itShouldDeleteAllProject() {
        projectRepository.deleteAll();
        Assertions.assertTrue(projectRepository.findAll().isEmpty());
    }
}
