package com.admin.dashboard.be.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleDTO {
    @NotEmpty(message = "Role name is required")
    private String name;
}
