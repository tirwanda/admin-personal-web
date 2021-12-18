package com.admin.dashboard.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
@Builder
@Entity
public class Tag extends BaseEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Tag name is required")
    private String name;

    @JsonIgnore
    @OneToMany(
            mappedBy = "tag",
            cascade = CascadeType.ALL
    )
    private List<Project> projects = new ArrayList<>();

    public void addProjectToTag(Project project) {
        this.projects.add(project);
        project.setTag(this);
    }

    public void removeProjectFromTag(Project project) {
        this.projects.remove(project);
        project.removeTag();
    }
}
