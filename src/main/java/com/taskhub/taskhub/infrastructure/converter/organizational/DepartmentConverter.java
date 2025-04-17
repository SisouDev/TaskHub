package com.taskhub.taskhub.infrastructure.converter.organizational;

import com.taskhub.taskhub.domain.dto.request.organizational.DepartmentRequestDTO;
import com.taskhub.taskhub.domain.dto.response.organizational.DepartmentResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.entities.organizational.Department;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {

    private final UserRepository userRepository;

    public DepartmentConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Department toEntity(DepartmentRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        Department department = new Department();
        department.setDescription(requestDTO.description());
        department.setName(requestDTO.name());
        department.setIconUrl(requestDTO.iconUrl());

        User manager = userRepository.findById(requestDTO.managerId()).orElse(null);
        department.setManager(manager);

        return department;
    }

    public DepartmentResponseDTO toResponseDTO(Department department) {
        if (department == null) {
            return null;
        }

        String managerName = department.getManager() != null ? department.getManager().getFirstName() + " " + department.getManager().getLastName() : null;

        return new DepartmentResponseDTO(
                department.getId(),
                department.getName(),
                department.getDescription(),
                department.getIconUrl(),
                managerName
        );
    }

}
