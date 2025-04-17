package com.taskhub.taskhub.domain.entities.projectmanagement;

import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.entities.organizational.Department;
import com.taskhub.taskhub.domain.entities.taskmanagement.Task;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column
    private LocalDateTime creationDate;

    @ManyToMany(mappedBy = "projects")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "leader_id", nullable = false)
    private User leader;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "project")
    @ToString.Exclude
    private Set<Task> tasks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "project_category_id")
    private ProjectCategory projectCategory;
}
