package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class TestRoleRepository {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

    Role adminRole;
    Role userRole;

    @BeforeEach
    public void init() {
        adminRole = Role.builder().name("ADMIN_ROLE").build();
        entityManager.persist(adminRole);

        userRole = Role.builder().name("USER_ROLE").build();
        entityManager.persist(userRole);
    }

    @Test
    public void itShouldSaveRole() {
        Role newRole = Role.builder().name("TEST_ROLE").build();
        Role roleSave = roleRepository.save(newRole);

        Assertions.assertTrue(roleSave.getId() > 0);
        Assertions.assertEquals("TEST_ROLE", roleSave.getName());
    }

    @Test
    public void itShouldReturnFalseIfRoleIsEmpty() {
        List<Role> roles = roleRepository.findAll();
        Assertions.assertFalse(roles.isEmpty());
    }

    @Test
    public void itShouldFindAllRole() {
        List<Role> roles = roleRepository.findAll();
        Assertions.assertEquals(2, roles.size());
        Assertions.assertTrue(roles.containsAll(List.of(adminRole, userRole)));
    }

    @Test
    public void itShouldFindRoleById(){
        Long roleId = adminRole.getId();
        Assertions.assertEquals(adminRole, roleRepository.findById(roleId).orElse(null));
    }

    @Test
    public void itShouldUpdatedRole() {
        Long roleId = adminRole.getId();
        Role roleWillUpdated = roleRepository.findById(roleId).orElse(null);

        assert roleWillUpdated != null;
        roleWillUpdated.setName("ROLE_UPDATED");
        roleRepository.save(roleWillUpdated);
        Role roleUpdated = roleRepository.findById(roleId).orElse(null);

        assert roleUpdated != null;
        Assertions.assertEquals("ROLE_UPDATED", roleUpdated.getName());
    }

    @Test
    public void itShouldDeleteRoleById() {
        Long roleId = adminRole.getId();
        roleRepository.deleteById(roleId);
        Assertions.assertFalse(roleRepository.findAll().contains(adminRole));
    }

    @Test
    public void itShouldDeleteAllRole() {
        roleRepository.deleteAll();
        Assertions.assertTrue(roleRepository.findAll().isEmpty());
    }
}
