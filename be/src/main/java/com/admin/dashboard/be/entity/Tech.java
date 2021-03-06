package com.admin.dashboard.be.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

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
@RedisHash("Tech")
public class Tech extends BaseEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long techId;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Tech name is required")
    private String name;

    @ManyToMany(mappedBy = "techList")
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

    private String imageURL;

    public void addProject(Project project) {
        this.projects.add(project);
        project.getTechList().add(this);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
        project.getTechList().remove(this);
    }
}
