package com.admin.dashboard.be.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Project extends BaseEntity<String> implements Serializable {

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

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
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
