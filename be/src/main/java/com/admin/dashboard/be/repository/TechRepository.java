package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechRepository extends JpaRepository<Tech, Long> {
    Tech findByName(String name);
}
