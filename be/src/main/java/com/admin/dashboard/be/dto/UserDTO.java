package com.admin.dashboard.be.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "email is required")
    @Email(message = "email is not valid")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;
}
