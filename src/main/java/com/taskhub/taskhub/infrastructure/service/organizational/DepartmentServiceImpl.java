package com.taskhub.taskhub.infrastructure.service.organizational;

import com.taskhub.taskhub.domain.dto.request.organizational.DepartmentRequestDTO;
import com.taskhub.taskhub.domain.dto.response.organizational.DepartmentResponseDTO;
import com.taskhub.taskhub.domain.entities.organizational.Department;
import com.taskhub.taskhub.domain.repository.organizational.DepartmentRepository;
import com.taskhub.taskhub.domain.service.organizational.DepartmentService;
import com.taskhub.taskhub.exceptions.organizational.DepartmentNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.organizational.DepartmentConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentConverter departmentConverter) {
        this.departmentRepository = departmentRepository;
        this.departmentConverter = departmentConverter;
    }

    @Override
    public DepartmentResponseDTO create(DepartmentRequestDTO dto) {
        logger.info("Creating department: {}", dto.name());
        Department department = departmentConverter.toEntity(dto);
        department = departmentRepository.save(department);
        return departmentConverter.toResponseDTO(department);
    }

    @Override
    public DepartmentResponseDTO update(Long id, DepartmentRequestDTO dto) {
        logger.info("Updating department with ID: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with ID: " + id));

        department.setName(dto.name());
        department = departmentRepository.save(department);
        return departmentConverter.toResponseDTO(department);
    }

    @Override
    public DepartmentResponseDTO getById(Long id) {
        logger.info("Fetching department with ID: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with ID: " + id));
        return departmentConverter.toResponseDTO(department);
    }

    @Override
    public List<DepartmentResponseDTO> getAll() {
        logger.info("Fetching all departments");
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(departmentConverter::toResponseDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting department with ID: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found with ID: " + id));
        departmentRepository.delete(department);
    }
}
