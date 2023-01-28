package com.example.internshipmanagement.service;

import com.example.internshipmanagement.dto.PositionDto;
import com.example.internshipmanagement.dto.UniversityDto;
import com.example.internshipmanagement.mapper.UniversityMapper;
import com.example.internshipmanagement.model.University;
import com.example.internshipmanagement.repository.UniversityRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    private final UniversityRepository repository;
    private final UniversityMapper mapper;

    public UniversityService(UniversityRepository repository) {
        this.repository = repository;
        this.mapper = new UniversityMapper();
    }

    public Specification<University> search(String field, String key) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field), "%" + key + "%");
    }

    public Page<University> getAll(Pageable pageable, String field, String key, int page, int size) {
        Specification<University> where = null;
        if (!StringUtils.isEmpty(key)) where = search(field, key);
        Sort sortable = Sort.by("id").ascending();
        pageable = PageRequest.of(page, size, sortable);
        return repository.findAll(where, pageable);
    }

    public UniversityDto getUniversityById(Long id) {
        University university = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University is not found"));
        return mapper.toDto(university);
    }

    public void save(UniversityDto dto) {
        repository.save(mapper.toEntity(dto));
    }

    public void update(Long id, UniversityDto universityDto) {
        University university = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id " + id));
        repository.save(mapper.toEntity(university, universityDto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<UniversityDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
