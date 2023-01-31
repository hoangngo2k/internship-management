package com.example.internshipmanagement.service;

import com.example.internshipmanagement.dto.CompanyCardDto;
import com.example.internshipmanagement.mapper.CompanyCardMapper;
import com.example.internshipmanagement.model.CompanyCard;
import com.example.internshipmanagement.repository.CompanyCardRepository;
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
public class CompanyCardService {
    private final CompanyCardRepository repository;
    private final CompanyCardMapper mapper;

    public CompanyCardService(CompanyCardRepository repository) {
        this.repository = repository;
        this.mapper = new CompanyCardMapper();
    }

    public Specification<CompanyCard> search(String field, String key) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(field), key);
    }

    public Page<CompanyCard> getAll(Pageable pageable, String key, int page, int size, String sort, String field) {
        Specification<CompanyCard> where = null;
        if (!StringUtils.isEmpty(key)) where = search("id", key);
        Sort orders = null;
        if (sort.equals("asc"))
            orders = Sort.by(field).ascending();
        if (sort.equals("desc"))
            orders = Sort.by(field).descending();
        if (orders != null)
            pageable = PageRequest.of(page, size, orders);
        return repository.findAll(where, pageable);
    }

    public List<CompanyCardDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public CompanyCardDto getCompanyCardById(String id) {
        CompanyCard companyCard = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company card is not found"));
        return mapper.toDto(companyCard);
    }

    public void save(CompanyCardDto dto) {
        repository.save(mapper.toEntity(dto));
    }

    public void update(String id, CompanyCardDto companyCardDto) {
        CompanyCard companyCard = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company card not found with id " + id));
        repository.save(mapper.toEntity(companyCard, companyCardDto));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
