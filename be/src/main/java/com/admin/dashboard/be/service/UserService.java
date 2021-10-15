package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    void saveProjectToUser(Long projectId, String username);
}
