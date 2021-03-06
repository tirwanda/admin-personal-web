package com.admin.dashboard.be.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity<String> implements Serializable {

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
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Project> projects;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "user_skill_map",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "skillId")
    )
    private List<Skill> skills = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    private String profileImage;

    public void addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getUsers().add(this);
    }

    public void removeSkill(Skill skill) {
        this.skills.remove(skill);
        skill.getUsers().remove(this);
    }
}
