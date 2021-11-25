package com.admin.dashboard.be.entity;

import com.fasterxml.jackson.annotation.*;
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
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "techId"
//)
public class Tech implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long techId;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Tech name is required")
    private String name;

    @ManyToMany(mappedBy = "techList")
//    @JsonBackReference
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

    private String imageURL;
}
