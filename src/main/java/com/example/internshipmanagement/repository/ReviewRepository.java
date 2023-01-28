package com.example.internshipmanagement.repository;

import com.example.internshipmanagement.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface ReviewRepository extends JpaRepository<Reviews, Long>, JpaSpecificationExecutor<Reviews> {
}
