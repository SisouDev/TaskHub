package com.taskhub.taskhub.domain.service.organizational;

import com.taskhub.taskhub.domain.dto.response.organizational.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> getAll();
    RoleResponseDTO getById(Long id);
}
