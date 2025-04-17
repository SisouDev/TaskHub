package com.taskhub.taskhub.domain.service.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TagRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TagResponseDTO;

import java.util.List;

public interface TagService {
    TagResponseDTO create(TagRequestDTO dto);
    TagResponseDTO update(Long id, TagRequestDTO dto);
    TagResponseDTO getById(Long id);
    List<TagResponseDTO> getAll();
    void delete(Long id);
}
