package com.example.internshipmanagement.repository;

import com.example.internshipmanagement.model.InternshipTimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipTimeSheetRepository extends JpaRepository<InternshipTimeSheet, Long>, JpaSpecificationExecutor<InternshipTimeSheet> {
}
