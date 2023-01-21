package com.example.internshipmanagement.service;

import com.example.internshipmanagement.enums.ERole;
import com.example.internshipmanagement.model.Role;
import com.example.internshipmanagement.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Optional<Role> getRoleByName(ERole role) {
        return repository.findByRole(role);
    }
}
