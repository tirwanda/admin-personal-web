package com.admin.dashboard.be.dto;

import com.admin.dashboard.be.entity.Project;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class TagDTO {
    private Long tagId;

    @NotEmpty(message = "Tag name is required")
    private String name;

    private List<Project> projects = new ArrayList<>();
}
