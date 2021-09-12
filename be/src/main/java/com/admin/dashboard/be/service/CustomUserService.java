package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.User;
import com.admin.dashboard.be.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userDetailRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }
}
