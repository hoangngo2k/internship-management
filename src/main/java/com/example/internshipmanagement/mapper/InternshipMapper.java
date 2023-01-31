package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.InternshipDto;
import com.example.internshipmanagement.model.Internship;

public class InternshipMapper {

    public Internship toEntity(InternshipDto dto) {
        Internship entity = new Internship();
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
        entity.setBirthday(dto.getBirthday());
        entity.setIdentifyCard(dto.getIdentifyCard());
        entity.setStatus(dto.getStatus());
        entity.setLevel(dto.getLevel());
        entity.setCompanyCard(dto.getCompanyCard());
        entity.setPosition(dto.getPosition());
        entity.setUniversity(dto.getUniversity());
        return entity;
    }

    public Internship toEntity(Internship entity, InternshipDto dto) {
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setFullName(dto.getFullName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRoles(dto.getRoles());
        entity.setDelFlg(dto.isDelFlg());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        entity.setBirthday(dto.getBirthday());
        entity.setIdentifyCard(dto.getIdentifyCard());
        entity.setStatus(dto.getStatus());
        entity.setLevel(dto.getLevel());
        entity.setCompanyCard(dto.getCompanyCard());
        entity.setPosition(dto.getPosition());
        entity.setUniversity(dto.getUniversity());
        return entity;
    }

    public InternshipDto toDto(Internship entity) {
        InternshipDto dto = new InternshipDto();
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
        dto.setBirthday(entity.getBirthday());
        dto.setIdentifyCard(entity.getIdentifyCard());
        dto.setStatus(entity.getStatus());
        dto.setLevel(entity.getLevel());
        dto.setCompanyCard(entity.getCompanyCard());
        dto.setPosition(entity.getPosition());
        dto.setUniversity(entity.getUniversity());
        return dto;
    }
}
