package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void injectedComponentsRoleRepositoryAreNotNull() {
        assertThat(roleRepository).isNotNull();
    }

    @Test
    void testSaveRole() {
        Role role = Role.builder().name("ADMIN_ROLE").build();
        Role actual = roleRepository.save(role);

        assertThat(actual).isEqualTo(role);
    }
    @Test
    void testFindByName() {
        Role role = Role.builder().name("ADMIN_ROLE").build();
        Role expect = roleRepository.save(role);
        Role actual = roleRepository.findByName("ADMIN_ROLE");

        assertThat(actual).isEqualTo(expect);
    }
}