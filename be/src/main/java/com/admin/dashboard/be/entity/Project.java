package com.admin.dashboard.be.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;

    @Column(unique = true)
    @NotEmpty(message = "Title is required")
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descriptions;

    @ManyToMany
    @JoinTable (
            name = "project_tech_map",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tech_id")
    )
    private List<Tech> techList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id", referencedColumnName = "tagId")
    private Tag tag;

    private String github;
    private String demo;

    @OneToMany(targetEntity = ProjectImage.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "Project_Image_FK", referencedColumnName = "projectId")
    private List<ProjectImage> projectImageList;
}
