package com.taskhub.taskhub.domain.repository.organizational;

import com.taskhub.taskhub.domain.entities.organizational.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
