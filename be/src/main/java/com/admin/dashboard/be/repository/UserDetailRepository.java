package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
