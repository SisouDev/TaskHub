package com.taskhub.taskhub.infrastructure.converter.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TagRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TagResponseDTO;
import com.taskhub.taskhub.domain.entities.taskmanagement.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagConverter {
    public Tag toEntity(TagRequestDTO requestDTO) {
        if (requestDTO == null) return null;
        Tag tag = new Tag();
        tag.setName(requestDTO.name());
        return tag;
    }

    public TagResponseDTO toResponseDTO(Tag tag) {
        if (tag == null) return null;
        return new TagResponseDTO(tag.getId(), tag.getName());
    }
}
