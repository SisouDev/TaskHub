package com.taskhub.taskhub.domain.repository.taskmanagement;

import com.taskhub.taskhub.domain.entities.taskmanagement.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCategoriesId(Long categoryId);
}
