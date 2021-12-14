package com.admin.dashboard.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
            mappedBy = "tag"
    )
    private List<Project> projects = new ArrayList<>();
}
