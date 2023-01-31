package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.MentorDto;
import com.example.internshipmanagement.model.Mentor;

public class MentorMapper extends UserMapper{

    public Mentor toEntity(MentorDto dto) {
        Mentor entity = new Mentor();
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
        entity.setMaxInternship(dto.getMaxInternship());
        entity.setActiveFlg(dto.isActiveFlg());
        return entity;
    }

    public MentorDto toDto(Mentor entity) {
        MentorDto dto = new MentorDto();
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
        dto.setMaxInternship(entity.getMaxInternship());
        dto.setActiveFlg(entity.isActiveFlg());
        return dto;
    }

    public Mentor toEntity(Mentor entity, MentorDto dto) {
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getFullName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRoles(dto.getRoles());
        entity.setDelFlg(dto.isDelFlg());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        entity.setMaxInternship(dto.getMaxInternship());
        entity.setActiveFlg(dto.isActiveFlg());
        return entity;
    }
}
