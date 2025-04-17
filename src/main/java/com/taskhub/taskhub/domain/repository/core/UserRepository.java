package com.taskhub.taskhub.domain.repository.core;

import com.taskhub.taskhub.domain.entities.core.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
