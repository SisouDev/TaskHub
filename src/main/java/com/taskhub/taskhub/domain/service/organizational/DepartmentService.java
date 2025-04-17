package com.taskhub.taskhub.domain.service.organizational;

import com.taskhub.taskhub.domain.dto.request.organizational.DepartmentRequestDTO;
import com.taskhub.taskhub.domain.dto.response.organizational.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDTO create(DepartmentRequestDTO dto);
    DepartmentResponseDTO update(Long id, DepartmentRequestDTO dto);
    DepartmentResponseDTO getById(Long id);
    List<DepartmentResponseDTO> getAll();
    void delete(Long id);
}
