package com.example.internshipmanagement.repository;

import com.example.internshipmanagement.model.CompanyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCardRepository extends JpaRepository<CompanyCard, String>, JpaSpecificationExecutor<CompanyCard> {
}
