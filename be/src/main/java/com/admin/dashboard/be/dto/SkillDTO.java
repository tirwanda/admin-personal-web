package com.admin.dashboard.be.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SkillDTO {

    @NotEmpty(message = "Skill id is required")
    private Long skillId;

    @NotEmpty(message = "Skill name is required")
    private String name;

    private String imageUrl;
}
