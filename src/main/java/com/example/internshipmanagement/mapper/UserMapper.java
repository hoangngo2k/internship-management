package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.UserDto;
import com.example.internshipmanagement.model.User;

public class UserMapper {

    public User toEntity(UserDto dto) {
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setFull_name(dto.getFull_name());
        entity.setPhone_number(dto.getPhone_number());
        entity.setRoles(dto.getRoles());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setCreate_id(dto.getCreate_id());
        entity.setCreate_at(dto.getCreate_at());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }

    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setFull_name(entity.getFull_name());
        dto.setPhone_number(entity.getPhone_number());
        dto.setRoles(entity.getRoles());
        dto.setIs_del_flg(entity.isIs_del_flg());
        dto.setCreate_id(entity.getCreate_id());
        dto.setCreate_at(entity.getCreate_at());
        dto.setModified_id(entity.getModified_id());
        dto.setModified_at(entity.getModified_at());
        return dto;
    }

    public User toEntity(User entity, UserDto dto) {
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setFull_name(dto.getFull_name());
        entity.setPhone_number(dto.getPhone_number());
        entity.setRoles(dto.getRoles());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setCreate_id(dto.getCreate_id());
        entity.setCreate_at(dto.getCreate_at());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }
}
