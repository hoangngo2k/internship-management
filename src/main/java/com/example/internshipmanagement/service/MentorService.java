package com.example.internshipmanagement.service;

import com.example.internshipmanagement.dto.MentorDto;
import com.example.internshipmanagement.mapper.MentorMapper;
import com.example.internshipmanagement.model.Mentor;
import com.example.internshipmanagement.repository.MentorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MentorService {

    private final MentorRepository mentorRepository;
    private final MentorMapper mapper;

    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
        this.mapper = new MentorMapper();
    }

    public Specification<Mentor> search(String field, String key) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field), "%" + key + "%");
    }

    public MentorDto getMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor is not found"));
        return mapper.toDto(mentor);
    }

    public Page<Mentor> getAll(Pageable pageable, String field, String key, int page, int size) {
        Specification<Mentor> where = null;
        if (!StringUtils.isEmpty(key))
            where = search(field, key);
        Sort sortable = Sort.by("id").ascending();
        pageable = PageRequest.of(page, size, sortable);
        return mentorRepository.findAll(where, pageable);
    }

    public void save(MentorDto dto) {
        mentorRepository.save(mapper.toEntity(dto));
    }

    public void update(Long id, MentorDto dto) {
        Mentor entity = mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor not found with id " + id));
        mentorRepository.save(mapper.toEntity(entity, dto));
    }

    public void delete(Long id) {
        mentorRepository.deleteById(id);
    }
}
