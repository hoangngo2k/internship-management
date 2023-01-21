package com.example.internshipmanagement.service;

import com.example.internshipmanagement.dto.ReviewsDto;
import com.example.internshipmanagement.mapper.ReviewsMapper;
import com.example.internshipmanagement.model.Reviews;
import com.example.internshipmanagement.repository.ReviewRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository repository;
    private final ReviewsMapper mapper;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
        this.mapper = new ReviewsMapper();
    }

    public Specification<Reviews> search(String field, String key) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field), "%" + key + "%");
    }

    public Page<Reviews> getAll(Pageable pageable, String field, String key, int page, int size) {
        Specification<Reviews> where = null;
        if (!StringUtils.isEmpty(key)) where = search(field, key);
        Sort sortable = Sort.by("id").ascending();
        pageable = PageRequest.of(page, size, sortable);
        return repository.findAll(where, pageable);
    }

    public ReviewsDto getReviewById(Long id) {
        Reviews reviews = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review is not found"));
        return mapper.toDto(reviews);
    }

    public void save(ReviewsDto dto) {
        repository.save(mapper.toEntity(dto));
    }

    public void update(Long id, ReviewsDto reviewsDto) {
        Reviews reviews = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
        repository.save(mapper.toEntity(reviews, reviewsDto));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
