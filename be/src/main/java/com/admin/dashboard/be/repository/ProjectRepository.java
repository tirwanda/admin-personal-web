package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByTitle(String title);
    Project findProjectByProjectId(Long id);

    @Query("SELECT p FROM Project p where p.tag.tagId = :tagId")
    public List<Project> findProjectByTag(@PathParam("tagId") Long tagId);
}
