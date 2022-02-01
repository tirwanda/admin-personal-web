package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class TestUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private User user1;
    private User user2;
    private Long userId;

    @BeforeEach
    public void init() {
        user1 = User.builder()
                .username("tirwanda")
                .name("tirwanda")
                .title("Back End")
                .email("tirwanda@gmail.com")
                .password("123456")
                .build();
        entityManager.persist(user1);

        user2 = User
                .builder()
                .username("dwi")
                .name("dwi")
                .email("dwi@gmail.com")
                .title("Front End")
                .password("123456")
                .build();
        entityManager.persist(user2);

        userId = user1.getUserId();
    }

    @Test
    public void itShouldSaveUser() {
        User user = User
                .builder()
                .username("edho")
                .name("edho")
                .email("edho@gmail.com")
                .title("FullStack")
                .password("123456")
                .build();
        User actualUser = userRepository.save(user);

        Assertions.assertTrue(actualUser.getUserId() > 0);
        Assertions.assertEquals("edho", actualUser.getName());
    }

    @Test
    public void itShouldReturnFalseWhenIfUserIsEmpty() {
        Assertions.assertFalse(userRepository.findAll().isEmpty());
    }

    @Test
    public void itShouldReturnListOfUser() {
        List<User> userList = userRepository.findAll();
        Assertions.assertTrue(userList.containsAll(List.of(user1, user2)));
    }

    @Test
    public void itShouldReturnUserById() {
        User actualUser = userRepository.findById(userId).orElse(null);
        Assertions.assertTrue(userRepository.findAll().contains(actualUser));
        assert actualUser != null;
        Assertions.assertEquals("tirwanda", actualUser.getUsername());
    }

    @Test
    public void itShouldReturnUserByUsername() {
        User actualUser = userRepository.findByUsername("tirwanda");
        Assertions.assertEquals("tirwanda", actualUser.getUsername());
    }

    @Test
    public void itShouldUpdateUser() {
        User userWillUpdate = userRepository.findById(userId).orElse(null);
        assert userWillUpdate != null;
        userWillUpdate.setName("tirwanda update");
        userWillUpdate.setTitle("Title Update");
        User actualUser = userRepository.save(userWillUpdate);

        Assertions.assertEquals("Title Update", actualUser.getTitle());
        Assertions.assertEquals("tirwanda update", actualUser.getName());
    }

    @Test
    public void itShouldDeleteUserById() {
        userRepository.deleteById(userId);
        Assertions.assertFalse(userRepository.findAll().contains(user1));
    }

    @Test
    public void itShouldDeleteAllUser() {
        userRepository.deleteAll();
        Assertions.assertTrue(userRepository.findAll().isEmpty());
    }
}
