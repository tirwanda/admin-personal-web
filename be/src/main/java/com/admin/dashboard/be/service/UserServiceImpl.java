package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Role;
import com.admin.dashboard.be.entity.Skill;
import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.ProjectRepository;
import com.admin.dashboard.be.repository.RoleRepository;
import com.admin.dashboard.be.repository.SkillRepository;
import com.admin.dashboard.be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProjectRepository projectRepository;
    private final PasswordEncoder passwordEncoder;
    private final SkillRepository skillRepository;

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
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

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
        User userUpdate = userRepository.findById(user.getUserId()).orElse(null);
        assert userUpdate != null;
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
    @Cacheable(value = "User", key = "#username")
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    @Cacheable("Users")
    public List<User> getUsers() {
        log.info("Fetching all user");
        return userRepository.findAll();
    }

    @Override
    public void uploadUserProfileImage(Long userId, String fileName) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        user.setProfileImage(fileName);
        userRepository.save(user);
    }


    @Override
    public void saveProjectToUser(Long projectId, String username) {
        User user = userRepository.findByUsername(username);
        Project project = projectRepository.findProjectByProjectId(projectId);

        user.getProjects().add(project);
    }

    @Override
    @Cacheable(value = "User", key = "#userId")
    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User addSkillToUser(Long userId, Long skillId) {
        User user = userRepository.findById(userId).orElse(null);
        Skill skill = skillRepository.findById(skillId).orElse(null);

        assert user != null;
        user.addSkill(skill);

        return user;
    }
}