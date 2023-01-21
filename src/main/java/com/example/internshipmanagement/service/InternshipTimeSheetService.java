package com.example.internshipmanagement.service;

import com.example.internshipmanagement.dto.InternshipTimeSheetDto;
import com.example.internshipmanagement.mapper.InternshipTimeSheetMapper;
import com.example.internshipmanagement.model.InternshipTimeSheet;
import com.example.internshipmanagement.repository.InternshipTimeSheetRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class InternshipTimeSheetService {

    private final InternshipTimeSheetRepository repository;
    private final InternshipTimeSheetMapper mapper;

    public InternshipTimeSheetService(InternshipTimeSheetRepository repository) {
        this.repository = repository;
        this.mapper = new InternshipTimeSheetMapper();
    }

    public Specification<InternshipTimeSheet> search(String field, String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field), "%" + keyword + "%");
    }

    public Page<InternshipTimeSheet> getAll(Pageable pageable, String field, String key, int page, int size) {
        Specification<InternshipTimeSheet> where = null;
        if (!StringUtils.isEmpty(key))
            where = search(field, key);
        Sort sortable = Sort.by("id").ascending();
        pageable = PageRequest.of(page, size, sortable);
        return repository.findAll(where, pageable);
    }

    public InternshipTimeSheetDto getTimesheetById(Long id) {
        InternshipTimeSheet timeSheet = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found with id " + id));
        return mapper.toDto(timeSheet);
    }

    public void save(InternshipTimeSheetDto dto) {
        repository.save(mapper.toEntity(dto));
    }

    public void update(Long id, InternshipTimeSheetDto dto) {
        InternshipTimeSheet internshipTimeSheet = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Timesheet not found with id " + id));
        repository.save(mapper.toEntity(internshipTimeSheet, dto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
