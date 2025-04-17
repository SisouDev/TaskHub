package com.taskhub.taskhub.infrastructure.service.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TagRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TagResponseDTO;
import com.taskhub.taskhub.domain.entities.taskmanagement.Tag;
import com.taskhub.taskhub.domain.repository.taskmanagement.TagRepository;
import com.taskhub.taskhub.domain.service.taskmanagement.TagService;
import com.taskhub.taskhub.exceptions.taskmanagement.TagAlreadyExistsException;
import com.taskhub.taskhub.exceptions.taskmanagement.TagNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.taskmanagement.TagConverter;
import com.taskhub.taskhub.infrastructure.service.projectmanagement.ProjectServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final Logger logger = LoggerFactory.getLogger(TagServiceImpl.class);
    private final TagRepository tagRepository;
    private final TagConverter tagConverter;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagConverter tagConverter) {
        this.tagRepository = tagRepository;
        this.tagConverter = tagConverter;
    }

    @Override
    public TagResponseDTO create(TagRequestDTO dto) {
        logger.info("Attempting to create a new tag with the following details: {}", dto);

        try {
            if (tagRepository.existsByName(dto.name())) {
                logger.warn("Tag creation failed: Tag with name '{}' already exists.", dto.name());
                throw new TagAlreadyExistsException("Tag with name '" + dto.name() + "' already exists.");
            }

            Tag tag = tagConverter.toEntity(dto);
            tag = tagRepository.save(tag);

            logger.info("Tag successfully created with ID: {}", tag.getId());

            return tagConverter.toResponseDTO(tag);
        } catch (TagAlreadyExistsException e) {
            logger.error("Tag creation failed: Tag with name '{}' already exists.", dto.name(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while creating tag", e);
            throw new RuntimeException("Unexpected error occurred while creating tag", e);
        }
    }

    @Override
    public TagResponseDTO update(Long id, TagRequestDTO dto) {
        logger.info("Attempting to update tag with ID: {}", id);

        try {
            Tag tag = tagRepository.findById(id)
                    .orElseThrow(() -> new TagNotFoundException("Tag not found with ID: " + id));
            tag.setName(dto.name());
            tag = tagRepository.save(tag);
            logger.info("Tag updated successfully with ID: {}", id);

            return tagConverter.toResponseDTO(tag);
        } catch (TagNotFoundException e) {
            logger.warn("Update failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while updating tag with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while updating tag", e);
        }
    }

    @Override
    public TagResponseDTO getById(Long id) {
        logger.info("Attempting to get tag with ID: {}", id);

        try {
            Tag tag = tagRepository.findById(id)
                    .orElseThrow(() -> new TagNotFoundException("Tag not found with ID: " + id));

            return tagConverter.toResponseDTO(tag);
        } catch (TagNotFoundException e) {
            logger.warn("Fetch failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching tag with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while fetching tag", e);
        }
    }

    @Override
    public List<TagResponseDTO> getAll() {
        logger.info("Attempting to get all tags");

        try {
            List<Tag> tags = tagRepository.findAll();
            List<TagResponseDTO> tagDTOs = tags.stream()
                    .map(tagConverter::toResponseDTO)
                    .toList();

            logger.info("Retrieved {} tags", tagDTOs.size());
            return tagDTOs;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching all tags", e);
            throw new RuntimeException("Unexpected error occurred while fetching all tags", e);
        }
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete tag with ID: {}", id);

        try {
            Tag tag = tagRepository.findById(id)
                    .orElseThrow(() -> new TagNotFoundException("Tag not found with ID: " + id));

            tagRepository.delete(tag);
            logger.info("Tag deleted successfully with ID: {}", id);
        } catch (TagNotFoundException e) {
            logger.warn("Delete failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting tag with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while deleting tag", e);
        }
    }
}
