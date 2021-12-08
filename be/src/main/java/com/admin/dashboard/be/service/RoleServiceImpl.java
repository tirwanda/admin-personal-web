package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    private final UserService userService;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Cacheable(value = "Role", key = "#username")
    public List<Role> getRoleByUser(String username) {
        User user = userService.getUser(username);
        return (List<Role>) user.getRoles();
    }

    @Override
    @Cacheable(value = "Role", key = "#roleId")
    public Role getRole(Long roleId) {
        return roleRepository.getById(roleId);
    }

    @Override
    @Cacheable("Roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @CacheEvict(value = "Role", key = "#roleId")
    public String deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
        return "Role with id: " + roleId + " is Removed";
    }
}
