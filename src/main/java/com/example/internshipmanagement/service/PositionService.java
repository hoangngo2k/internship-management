package com.example.internshipmanagement.service;

import com.example.internshipmanagement.dto.PositionDto;
import com.example.internshipmanagement.mapper.PositionMapper;
import com.example.internshipmanagement.model.Position;
import com.example.internshipmanagement.repository.PositionRepository;
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
public class PositionService {

    private final PositionRepository repository;
    private final PositionMapper mapper;

    public PositionService(PositionRepository repository) {
        this.repository = repository;
        this.mapper = new PositionMapper();
    }

    public Specification<Position> search(String field, String key) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field), "%" + key + "%");
    }

    public Page<Position> getAll(Pageable pageable, String key, int page, int size, String sort, String field) {
        Specification<Position> where = null;
        if (!StringUtils.isEmpty(key)) where = search("name", key);
        Sort orders = null;
        if (sort.equals("asc"))
            orders = Sort.by(field).ascending();
        if (sort.equals("desc"))
            orders = Sort.by(field).descending();
        if (orders != null)
            pageable = PageRequest.of(page, size, orders);
        return repository.findAll(where, pageable);
    }

    public List<PositionDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PositionDto getPositionById(Long id) {
        Position position = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position is not found"));
        return mapper.toDto(position);
    }

    public void save(PositionDto dto) {
        repository.save(mapper.toEntity(dto));
    }

    public void update(Long id, PositionDto positionDto) {
        Position position = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found with id " + id));
        repository.save(mapper.toEntity(position, positionDto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
