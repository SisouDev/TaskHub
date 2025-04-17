package com.taskhub.taskhub.domain.repository.organizational;

import com.taskhub.taskhub.domain.entities.organizational.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
