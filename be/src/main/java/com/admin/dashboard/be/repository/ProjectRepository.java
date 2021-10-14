package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByTitle(String title);
}
