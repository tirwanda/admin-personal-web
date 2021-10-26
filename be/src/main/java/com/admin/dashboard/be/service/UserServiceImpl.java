package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.RoleRepository;
import com.admin.dashboard.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving user {} to database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User userUpdate = userRepository.findById(user.getUserId()).get();
        userUpdate.setName(user.getName());
        userUpdate.setTitle(user.getTitle());
        userUpdate.setAbout(user.getAbout());

        return userRepository.save(userUpdate);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role {} to database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all user");
        return userRepository.findAll();
    }

    @Override
    public void uploadUserProfileImage(Long id, MultipartFile file) {
        // 1. Check if image is not empty
        // 2. If file is an image
        // 3. The user exists in our database
        // 4. Grab some metadata from file if any
        // 5. store the image in database
    }

    @Override
    public void saveProjectToUser(Long projectId, String username) {
        User user = userRepository.findByUsername(username);
        Project project = projectRepository.findProjectByProjectId(projectId);

        user.getProjects().add(project);
    }
}