package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.InternshipDto;
import com.example.internshipmanagement.model.Internship;

public class InternshipMapper {

    public Internship toEntity(InternshipDto dto) {
        Internship entity = new Internship();
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
        entity.setBirthday(dto.getBirthday());
        entity.setIdentify_card(dto.getIdentify_card());
        entity.setStatus(dto.getStatus());
        entity.setLevel(dto.getLevel());
        return entity;
    }

    public Internship toEntity(Internship entity, InternshipDto dto) {
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setFull_name(dto.getFull_name());
        entity.setPhone_number(dto.getPhone_number());
        entity.setRoles(dto.getRoles());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        entity.setBirthday(dto.getBirthday());
        entity.setIdentify_card(dto.getIdentify_card());
        entity.setStatus(dto.getStatus());
        entity.setLevel(dto.getLevel());
        return entity;
    }

    public InternshipDto toDto(Internship entity) {
        InternshipDto dto = new InternshipDto();
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
        dto.setBirthday(entity.getBirthday());
        dto.setIdentify_card(entity.getIdentify_card());
        dto.setStatus(entity.getStatus());
        dto.setLevel(entity.getLevel());
        return dto;
    }
}
