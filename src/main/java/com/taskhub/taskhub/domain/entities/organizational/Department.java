package com.taskhub.taskhub.domain.entities.organizational;

import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.entities.projectmanagement.Project;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String iconUrl;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private Set<Project> projects = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private User manager;

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();
}
