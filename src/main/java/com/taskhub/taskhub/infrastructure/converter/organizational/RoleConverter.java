package com.taskhub.taskhub.infrastructure.converter.organizational;

import com.taskhub.taskhub.domain.dto.request.organizational.RoleRequestDTO;
import com.taskhub.taskhub.domain.dto.response.organizational.RoleResponseDTO;
import com.taskhub.taskhub.domain.entities.organizational.Department;
import com.taskhub.taskhub.domain.entities.organizational.Role;
import com.taskhub.taskhub.domain.repository.organizational.DepartmentRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    private final DepartmentRepository departmentRepository;

    public RoleConverter(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Role toEntity(RoleRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        Role role = new Role();
        role.setName(requestDTO.name());

        Department department = departmentRepository.findById(requestDTO.departmentId()).orElse(null);
        role.setDepartment(department);

        return role;
    }

    // Conversão de Role para RoleResponseDTO
    public RoleResponseDTO toResponseDTO(Role role) {
        if (role == null) {
            return null;
        }

        String departmentName = role.getDepartment() != null ? role.getDepartment().getName() : null;

        return new RoleResponseDTO(
                role.getId(),
                role.getName(),
                departmentName
        );
    }
}
