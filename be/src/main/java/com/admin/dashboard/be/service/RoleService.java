package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.Tag;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    List<Role> getRoleByUser(String username);
    Role getRole(Long roleId);
    List<Role> getAllRoles();
    String deleteRole(Long roleId);
}
