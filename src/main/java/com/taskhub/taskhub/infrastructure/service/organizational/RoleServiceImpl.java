package com.taskhub.taskhub.infrastructure.service.organizational;

import com.taskhub.taskhub.domain.dto.response.organizational.RoleResponseDTO;
import com.taskhub.taskhub.domain.entities.organizational.Role;
import com.taskhub.taskhub.domain.repository.organizational.RoleRepository;
import com.taskhub.taskhub.domain.service.organizational.RoleService;
import com.taskhub.taskhub.exceptions.organizational.RoleNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.organizational.RoleConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    @Override
    public List<RoleResponseDTO> getAll() {
        logger.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleConverter::toResponseDTO)
                .toList();
    }

    @Override
    public RoleResponseDTO getById(Long id) {
        logger.info("Fetching role with ID: {}", id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with ID: " + id));
        return roleConverter.toResponseDTO(role);
    }
}
