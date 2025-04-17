package com.taskhub.taskhub.domain.repository.projectmanagement;

import com.taskhub.taskhub.domain.entities.projectmanagement.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
