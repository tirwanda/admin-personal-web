package com.admin.dashboard.be.repository.dao;

import com.admin.dashboard.be.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final RedisTemplate redisTemplate;

    private static final String KEY = "USER";

    public UserDaoImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean saveUser(User user) {
        try {
            redisTemplate.opsForHash().put(KEY, user.getUserId().toString(), user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> fetchAllUser() {
        List users;
        users = redisTemplate.opsForHash().values(KEY);
        return  users;
    }

    @Override
    public User fetchUserById(Long id) {
        User user;
        user = (User) redisTemplate.opsForHash().get(KEY,id.toString());
        return user;
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            redisTemplate.opsForHash().delete(KEY,id.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(Long id, User user) {
        try {
            redisTemplate.opsForHash().put(KEY, id, user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
