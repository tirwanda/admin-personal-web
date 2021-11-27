package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void injectedComponentsUserRepositoryAreNotNull() {
        assertThat(userRepository).isNotNull();
        assertThat(passwordEncoder).isNotNull();
    }

    @Test
    void saveUser() {
        User user = User.builder()
                .name("Tirwanda")
                .username("tirwanda1")
                .title("Backend Engineer")
                .about("I am a Software Engineer with 1+ year experience")
                .email("tirwanda@mail.com")
                .password(passwordEncoder.encode("password"))
                .build();
        User expected = userRepository.save(user);
        assertThat(expected).isEqualTo(user);
    }

    @Test
    void findByUsername() {
        User user = User.builder()
                .name("Tirwanda")
                .username("tirwanda1")
                .title("Backend Engineer")
                .about("I am a Software Engineer with 1+ year experience")
                .email("tirwanda@mail.com")
                .password(passwordEncoder.encode("password"))
                .build();
        User expected = userRepository.save(user);
        User actual = userRepository.findByUsername("tirwanda1");

        assertThat(actual).isEqualTo(expected);
    }
}