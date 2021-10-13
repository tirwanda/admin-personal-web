package com.admin.dashboard.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {
    @Id
    private Long tagId;
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<Project> projects;
}
