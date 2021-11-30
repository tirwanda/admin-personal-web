package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.ProfileImageRepository;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.RoleRepository;
import com.admin.dashboard.be.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private RoleRepository roleRepository;
    @Mock private ProjectRepository projectRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private ProfileImageRepository profileImageRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, roleRepository, projectRepository, passwordEncoder, profileImageRepository);
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
    void loadUserByUsername() {
        userService.getUser("tirwanda");
        verify(userRepository).findByUsername("tirwanda");
    }

    @Test
    void saveUser() {
        User user = User.builder()
                .name("Dwi T")
                .username("dwi")
                .password(passwordEncoder.encode("password"))
                .email("dwi@mail.com")
                .build();
        userService.saveUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser() {
        User user = User.builder()
                .userId(10L)
                .name("Dwi T")
                .username("dwi")
                .password(passwordEncoder.encode("password"))
                .email("dwi@mail.com")
                .build();
        Mockito.when(userRepository.findByUsername("dwi")).thenReturn(user);

        User actual = userService.updateUser(user);
        assertThat(actual).isEqualTo(user);
    }

    @Test
    void saveRole() {
    }

    @Test
    void addRoleToUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void uploadUserProfileImage() {
    }

    @Test
    void saveProjectToUser() {
    }
}