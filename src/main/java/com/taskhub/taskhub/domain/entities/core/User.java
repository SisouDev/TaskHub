package com.taskhub.taskhub.domain.entities.core;

import com.taskhub.taskhub.domain.entities.organizational.Department;
import com.taskhub.taskhub.domain.entities.organizational.Role;
import com.taskhub.taskhub.domain.entities.projectmanagement.Project;
import com.taskhub.taskhub.domain.entities.taskmanagement.Task;
import com.taskhub.taskhub.domain.enums.Permission;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email.")
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private Permission permission;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_task",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    @ToString.Exclude
    private Set<Task> tasks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @ToString.Exclude
    private Set<Project> projects =  new HashSet<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<ActivityLog> logs = new HashSet<>();

    @OneToMany(mappedBy = "recipient")
    @ToString.Exclude
    private Set<Notification> notifications = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}
