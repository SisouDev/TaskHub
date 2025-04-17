package com.taskhub.taskhub.infrastructure.service.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TagRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TagResponseDTO;
import com.taskhub.taskhub.domain.service.taskmanagement.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Override
    public TagResponseDTO create(TagRequestDTO dto) {
        return null;
    }

    @Override
    public TagResponseDTO update(Long id, TagRequestDTO dto) {
        return null;
    }

    @Override
    public TagResponseDTO getById(Long id) {
        return null;
    }

    @Override
    public List<TagResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }
}
