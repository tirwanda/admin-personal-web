package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Project;
import com.admin.dashboard.be.entity.Tech;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findProjectByTitleContains(String title, Pageable pageable);
    Project findProjectByProjectId(Long id);

    @Query("SELECT p FROM Project p where p.tag.tagId = :tagId")
    public List<Project> findProjectByTag(@PathParam("tagId") Long tagId);

    @Query("SELECT p from Project p where :tech member of p.techList")
    public List<Project> findProjectByTech(@PathParam("tech") Tech tech);
}
