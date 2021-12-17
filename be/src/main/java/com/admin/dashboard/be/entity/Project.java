package com.admin.dashboard.be.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Proxy(lazy = false)
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
    @Fetch(FetchMode.JOIN)
    @JoinTable (
            name = "project_tech_map",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "tech_id")
    )
    private Set<Tech> techList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id", referencedColumnName = "tagId")
    private Tag tag;

    private String github;
    private String demo;

    @OneToMany(targetEntity = ProjectImage.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Project_Image_FK", referencedColumnName = "projectId")
    private List<ProjectImage> projectImageList;

    public void removeTag() {
        this.setTag(null);
    }

    public void addTech(Tech tech) {
        this.techList.add(tech);
        tech.getProjects().add(this);
    }

    public void removeTech(Tech tech) {
        this.techList.remove(tech);
        tech.getProjects().remove(this);
    }
}
