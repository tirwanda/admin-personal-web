package com.admin.dashboard.be.dto;

import com.admin.dashboard.be.entity.ProjectImage;
import com.admin.dashboard.be.entity.Tag;
import com.admin.dashboard.be.entity.Tech;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectDTO {
    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Description is required")
    private String description;

    private List<Tech> techList = new ArrayList<>();
    private Tag tag;
    private String github;
    private String demo;
    private List<ProjectImage> projectImageList;
}