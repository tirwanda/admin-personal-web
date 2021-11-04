package com.admin.dashboard.be.repository;

import com.admin.dashboard.be.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findTagByName(String name);
}
