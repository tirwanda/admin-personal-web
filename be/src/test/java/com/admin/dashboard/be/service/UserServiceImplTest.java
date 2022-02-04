package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.Skill;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private RoleRepository roleRepository;
    @Mock private ProjectRepository projectRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private SkillRepository skillRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private AutoCloseable autoCloseable;

    private User user1;
    private User user2;
    private Role role1;
    private Project project1;
    private Skill skill1;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(
                userRepository,
                roleRepository,
                projectRepository,
                passwordEncoder,
                skillRepository
        );

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
                .projects(new ArrayList<>())
                .skills(new ArrayList<>())
                .email("userdua@gmail.com")
                .build();

        role1 = Role.builder().name("ROLE_ADMIN").build();

        project1 = Project.builder()
                .title("Project 1")
                .descriptions("Project 1 description")
                .demo("www.google.com")
                .github("www.github.com")
                .build();

        skill1 = Skill.builder()
                .name("Java")
                .users(new ArrayList<>())
                .build();
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(userRepository).isNotNull();
        assertThat(roleRepository).isNotNull();
        assertThat(projectRepository).isNotNull();
        assertThat(passwordEncoder).isNotNull();
        assertThat(projectRepository).isNotNull();
        assertThat(userService).isNotNull();
    }

    @Test
    void itShouldSaveUser() {
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        User actualUser = userService.saveUser(user1);

        verify(userRepository, times(1)).save(user1);
        assertThat(actualUser).isEqualTo(user1);
    }

    @Test
    void itShouldUpdateUser() {
        Mockito.when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        user1.setName("user satu is updated");
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        User actualUser = userService.updateUser(user1);

        verify(userRepository, times(1)).findById(user1.getUserId());
        verify(userRepository, times(1)).save(user1);
        assertThat(actualUser.getName()).isEqualTo("user satu is updated");
    }

    @Test
    void itShouldSaveRole() {
        Mockito.when(roleRepository.save(role1)).thenReturn(role1);
        Role actualRole = userService.saveRole(role1);

        verify(roleRepository, times(1)).save(role1);
        assertThat(actualRole.getName()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    void itShouldAddingRoleToUser() {
        Mockito.when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
        Mockito.when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(role1);
        userService.addRoleToUser(user1.getUsername(), "ROLE_ADMIN");

        verify(userRepository, times(1)).findByUsername(user1.getUsername());
        verify(roleRepository, times(1)).findByName("ROLE_ADMIN");
        Assertions.assertThat(user1.getRoles()).contains(role1);
    }

    @Test
    void itShouldReturnUserByUsername() {
        when(userRepository.findByUsername("user1")).thenReturn(user1);
        User actualUser = userService.getUser("user1");

        verify(userRepository, times(1)).findByUsername("user1");
        assertThat(actualUser.getUsername()).isEqualTo(user1.getUsername());
    }

    @Test
    void itShouldReturnListOfUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        List<User> actualListOfUser = userService.getUsers();

        verify(userRepository, times(1)).findAll();
        assertThat(actualListOfUser).isEqualTo(Arrays.asList(user1, user2));
    }

    @Test
    void itShouldUploadImageAndSavingToUser() {
        Mockito.when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        userService.uploadUserProfileImage(user1.getUserId(), "image.jpg");

        verify(userRepository, times(1)).findById(user1.getUserId());
        verify(userRepository, times(1)).save(user1);
        assertThat(user1.getProfileImage()).isEqualTo("image.jpg");
    }

    @Test
    void itShouldSaveProjectToUser() {
        Mockito.when(projectRepository.findProjectByProjectId(project1.getProjectId())).thenReturn(project1);
        Mockito.when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
        userService.saveProjectToUser(project1.getProjectId(), user1.getUsername());

        verify(projectRepository, times(1)).findProjectByProjectId(project1.getProjectId());
        verify(userRepository, times(1)).findByUsername(user1.getUsername());
        Assertions.assertThat(user1.getProjects()).contains(project1);
    }

    @Test
    void itShouldReturnUserByUserId() {
        Mockito.when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        User actualUser = userService.getUserByUserId(user1.getUserId());

        verify(userRepository, times(1)).findById(user1.getUserId());
        assertThat(actualUser.getUsername()).isEqualTo(user1.getUsername());
    }

    @Test
    void itShouldAddSkillToUser() {
        Mockito.when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        Mockito.when(skillRepository.findById(skill1.getSkillId())).thenReturn(Optional.of(skill1));
        userService.addSkillToUser(user1.getUserId(), skill1.getSkillId());

        verify(userRepository, times(1)).findById(user1.getUserId());
        verify(skillRepository, times(1)).findById(skill1.getSkillId());
        Assertions.assertThat(user1.getSkills()).contains(skill1);
    }
}