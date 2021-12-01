package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.ProfileImageRepository;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.RoleRepository;
import com.admin.dashboard.be.repository.UserRepository;
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


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    void getUser() {
        User expectUser = User.builder()
                .username("tirwanda")
                .name("Edho Dwi Tirwanda")
                .email("edhodwtirwanda@gmail.com")
                .about("My About")
                .title("Backend Engineer")
                .password(passwordEncoder.encode("password"))
                .build();

        when(userRepository.findByUsername("tirwanda")).thenReturn(expectUser);
        User actualUser = userService.getUser("tirwanda");

        assertThat(actualUser).isEqualTo(expectUser);
    }

    @Test
    void getUsers() {
        User expectUser1 = User.builder()
                .username("tirwanda")
                .name("Edho Dwi Tirwanda")
                .email("edhodwtirwanda@gmail.com")
                .about("My About")
                .title("Backend Engineer")
                .password(passwordEncoder.encode("password"))
                .build();

        User expectUser2 = User.builder()
                .username("dwi")
                .name("Dwi Tirwanda")
                .email("dwtirwanda@gmail.com")
                .about("My About")
                .title("Full Stack")
                .password(passwordEncoder.encode("password"))
                .build();

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(expectUser1, expectUser2));

        List<User> actualListOfUser = userService.getUsers();
        assertThat(actualListOfUser).isEqualTo(Arrays.asList(expectUser1, expectUser2));
    }

}