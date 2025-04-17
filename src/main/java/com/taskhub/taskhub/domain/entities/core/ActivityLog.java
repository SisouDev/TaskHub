package com.taskhub.taskhub.domain.entities.core;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String activity;

    @Column
    @CreationTimestamp
    private LocalDateTime date;

    @Column
    private String entity;

    @Column
    private Long entityId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
