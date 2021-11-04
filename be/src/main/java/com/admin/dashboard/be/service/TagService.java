package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Tag;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);
    Tag getTagByProject(Long projectId);
    void addTagToProject(String tagName, String projectTitle);
    Tag getTag(Long tagId);
    List<Tag> getAllTags();
    void deleteTag(Long tagId);
}
