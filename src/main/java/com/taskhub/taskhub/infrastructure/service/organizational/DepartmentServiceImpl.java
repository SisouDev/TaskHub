package com.taskhub.taskhub.infrastructure.service.organizational;

import com.taskhub.taskhub.domain.dto.request.organizational.DepartmentRequestDTO;
import com.taskhub.taskhub.domain.dto.response.organizational.DepartmentResponseDTO;
import com.taskhub.taskhub.domain.service.organizational.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public DepartmentResponseDTO create(DepartmentRequestDTO dto) {
        return null;
    }

    @Override
    public DepartmentResponseDTO update(Long id, DepartmentRequestDTO dto) {
        return null;
    }

    @Override
    public DepartmentResponseDTO getById(Long id) {
        return null;
    }

    @Override
    public List<DepartmentResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
