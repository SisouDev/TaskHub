package com.taskhub.taskhub.domain.entities.organizational;

import com.taskhub.taskhub.domain.entities.core.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

}
