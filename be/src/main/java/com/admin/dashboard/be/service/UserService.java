package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    void uploadUserProfileImage(Long id, MultipartFile file);
    void saveProjectToUser(Long projectId, String username);
}
