package com.admin.dashboard.be.dto;

import com.admin.dashboard.be.entity.Project;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class TechDTO {

    private Long techId;
    @NotEmpty(message = "Tech name is required")
    private String name;
    private String imageURL;

    private List<Project> projects = new ArrayList<>();
}
