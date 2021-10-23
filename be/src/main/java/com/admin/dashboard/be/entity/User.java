package com.admin.dashboard.be.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String name;

    private String title;

    @Column(
            nullable = false,
            unique = true
    )
    @NotEmpty(message = "Email is Required")
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "Password is required")
    private String password;

    @Column(
            nullable = false,
            unique = true
    )
    @NotEmpty(message = "Email is Required")
    private String email;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String about;

    @OneToMany(
            targetEntity = Project.class,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
    )
    private List<Project> projects;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @Column(name = "image_profile")
    private String userProfileLink;
}
