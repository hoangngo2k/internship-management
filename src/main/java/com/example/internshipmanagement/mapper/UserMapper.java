package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.model.User;

public class UserMapper {

    public User toEntity(UserDto dto) {
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getFullName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRoles(dto.getRoles());
        entity.setDelFlg(dto.isDelFlg());
        entity.setCreateId(dto.getCreateId());
        entity.setCreateAt(dto.getCreateAt());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        return entity;
    }

    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setFullName(entity.getFullName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setRoles(entity.getRoles());
        dto.setDelFlg(entity.isDelFlg());
        dto.setCreateId(entity.getCreateId());
        dto.setCreateAt(entity.getCreateAt());
        dto.setModifiedId(entity.getModifiedId());
        dto.setModifiedAt(entity.getModifiedAt());
        return dto;
    }

    public User toEntity(User entity, UserDto dto) {
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getFullName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRoles(dto.getRoles());
        entity.setDelFlg(dto.isDelFlg());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        return entity;
    }
}
