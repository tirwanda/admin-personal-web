package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.ProfileImage;
import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.wrappers.ProfileImageWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    User uploadUserProfileImage(Long userId, ProfileImageWrapper profileImageWrapper);
    void saveProjectToUser(Long projectId, String username);
}
