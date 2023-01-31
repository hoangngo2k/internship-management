package com.example.internshipmanagement.service;

import com.example.internshipmanagement.dto.InternshipDto;
import com.example.internshipmanagement.mapper.InternshipMapper;
import com.example.internshipmanagement.model.Internship;
import com.example.internshipmanagement.repository.InternshipRepository;
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
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final InternshipMapper mapper;

    public InternshipService(InternshipRepository internshipRepository) {
        this.internshipRepository = internshipRepository;
        this.mapper = new InternshipMapper();
    }

    public Specification<Internship> search(String field, String key) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field), "%" + key + "%");
    }

    public Page<Internship> getAll(Pageable pageable, String key, int page, int size, String sort, String field) {
        Specification<Internship> where = null;
        if (!StringUtils.isEmpty(key))
            where = search("username", key);
        Sort orders = null;
        if (sort.equals("asc"))
            orders = Sort.by(field).ascending();
        if (sort.equals("desc"))
            orders = Sort.by(field).descending();
        if (orders != null)
            pageable = PageRequest.of(page, size, orders);
        return internshipRepository.findAll(where, pageable);
    }

    public List<InternshipDto> getAll() {
        return internshipRepository.findAll()
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public InternshipDto getInternshipById(Long id) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship is not found"));
        return mapper.toDto(internship);
    }

    public void save(InternshipDto dto) {
        internshipRepository.save(mapper.toEntity(dto));
    }

    public void update(Long id, InternshipDto dto) {
        Internship entity = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship is not found with id " + id));
        internshipRepository.save(mapper.toEntity(entity, dto));
    }

    public void delete(Long id) {
        internshipRepository.deleteById(id);
    }

    public boolean existedByUsername(String username) {
        return internshipRepository.existsByUsername(username);
    }
}
