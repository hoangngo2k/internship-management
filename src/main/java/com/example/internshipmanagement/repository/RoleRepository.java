package com.example.internshipmanagement.repository;

import com.example.internshipmanagement.model.Role;
import com.example.internshipmanagement.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(ERole role);
}
