package com.taskhub.taskhub.infrastructure.service.organizational;

import com.taskhub.taskhub.domain.dto.response.organizational.RoleResponseDTO;
import com.taskhub.taskhub.domain.service.organizational.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<RoleResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public RoleResponseDTO getById(Long id) {
        return null;
    }
}
