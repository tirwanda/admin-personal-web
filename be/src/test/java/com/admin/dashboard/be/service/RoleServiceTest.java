package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.RoleRepository;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class RoleServiceTest {
    @Mock private RoleRepository roleRepository;
    @Mock private UserService userService;

    @InjectMocks
    private RoleServiceImpl roleService;
    private AutoCloseable autoCloseable;

    private Role role1;
    private Role role2;
    private User user1;

    @BeforeEach
    void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        role1 = Role.builder().name("Role1").build();
        role2 = Role.builder().name("Role2").build();

        user1 = User.builder()
                .username("user1")
                .name("user satu")
                .password("12345")
                .title("Backend Engineer")
                .roles(List.of(role1, role2))
                .projects(new ArrayList<>())
                .skills(new ArrayList<>())
                .email("usersatu@gmail.com")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void injectMockAreNotNull() {
        assertThat(roleRepository).isNotNull();
        assertThat(userService).isNotNull();
    }

    @Test
    void itShouldSaveRole() {
        Mockito.when(roleRepository.save(role1)).thenReturn(role1);
        Role actualRole = roleService.saveRole(role1);

        verify(roleRepository, times(1)).save(role1);
        assertThat(actualRole.getName()).isEqualTo("Role1");
    }

    @Test
    void itShouldReturnRolesOfUser() {
        Mockito.when(userService.getUser("user1")).thenReturn(user1);
        List<Role> actualRoles = roleService.getRoleByUser("user1");

        verify(userService, times(1)).getUser("user1");
        assertThat(actualRoles.size()).isEqualTo(2);
        assertThat(actualRoles.containsAll(List.of(role1, role2))).isTrue();
    }

    @Test
    void itShouldReturnRole() {
        Mockito.when(roleRepository.getById(role1.getId())).thenReturn(role1);
        Role actualRole = roleService.getRole(role1.getId());

        verify(roleRepository, times(1)).getById(role1.getId());
        assertThat(actualRole).isEqualTo(role1);
    }

    @Test
    void itShouldReturnAllRoles() {
        Mockito.when(roleRepository.findAll()).thenReturn(List.of(role1, role2));
        List<Role> actualRoles = roleService.getAllRoles();

        verify(roleRepository, times(1)).findAll();
        assertThat(actualRoles.size()).isEqualTo(2);
        assertThat(actualRoles.containsAll(List.of(role1, role2))).isTrue();
    }
}
