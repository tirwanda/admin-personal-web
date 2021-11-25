package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Tech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechRepository extends JpaRepository<Tech, Long> {
    List<Tech> findByNameContains(String name);
    Tech findByName(String name);
}
