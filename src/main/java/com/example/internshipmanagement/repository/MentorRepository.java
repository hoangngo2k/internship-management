package com.example.internshipmanagement.repository;

import com.example.internshipmanagement.dto.MentorDto;
import com.example.internshipmanagement.model.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long>, JpaSpecificationExecutor<Mentor> {
    boolean existsByUsername(String username);
}
