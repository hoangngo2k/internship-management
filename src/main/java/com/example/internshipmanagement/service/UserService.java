package com.example.internshipmanagement.service;

import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.mapper.UserMapper;
import com.example.internshipmanagement.model.User;
import com.example.internshipmanagement.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new UserMapper();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDto.build(user);
    }

    public Specification<User> search(String field, String keyword) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field), "%" + keyword + "%");
    }

    public Page<User> getAll(Pageable pageable, String field, String keyword, int page, int size) {
        Specification<User> specification = null;
        if (!StringUtils.isEmpty(keyword))
            specification = search(field, keyword);
        pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return userRepository.findAll(specification, pageable);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User is not found with username " + username));
        return mapper.toDto(user);
    }

    public UserDto save(UserDto userDTO) {
        User user = mapper.toEntity(userDTO);
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return mapper.toDto(userRepository.save(user));
    }

    public void update(UserDto userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found id " + userDTO.getId()));
        userRepository.save(mapper.toEntity(user, userDTO));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
