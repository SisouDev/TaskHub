package com.taskhub.taskhub.domain.repository.taskmanagement;

import com.taskhub.taskhub.domain.entities.taskmanagement.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
