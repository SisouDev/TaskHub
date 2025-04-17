package com.taskhub.taskhub.domain.repository.projectmanagement;

import com.taskhub.taskhub.domain.entities.projectmanagement.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectCategoryRepository extends JpaRepository<ProjectCategory, Long> {
}
