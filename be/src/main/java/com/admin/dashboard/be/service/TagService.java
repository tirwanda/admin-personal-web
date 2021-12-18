package com.admin.dashboard.be.service;

import com.admin.dashboard.be.entity.Tag;

import java.util.List;

public interface TagService {
    Tag saveTag(Tag tag);
    Tag getTagByProject(Long projectId);
    Tag getTag(Long tagId);
    List<Tag> getAllTags();
    String deleteTag(Long tagId);
    String addProjectToTag(Long tagId, Long projectId);
    String removeProjectFromTag(Long projectId);
}
