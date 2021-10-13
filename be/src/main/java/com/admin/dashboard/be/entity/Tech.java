package com.admin.dashboard.be.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tech {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long techId;
    private String name;

//    @ManyToMany(mappedBy = "techList")
//    private List<Project> projects = new ArrayList<>();
}
