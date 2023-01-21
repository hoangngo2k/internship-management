package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.PositionDto;
import com.example.internshipmanagement.dto.UniversityDto;
import com.example.internshipmanagement.model.Position;
import com.example.internshipmanagement.model.University;

public class UniversityMapper {

    public University toEntity(UniversityDto dto) {
        University entity = new University();
        entity.setName(dto.getName());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setCreate_id(dto.getCreate_id());
        entity.setCreate_at(dto.getCreate_at());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }

    public University toEntity(University entity, UniversityDto dto) {
        entity.setName(dto.getName());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }

    public UniversityDto toDto(University entity) {
        UniversityDto dto = new UniversityDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setIs_del_flg(entity.isIs_del_flg());
        dto.setCreate_id(entity.getCreate_id());
        dto.setCreate_at(entity.getCreate_at());
        dto.setModified_id(entity.getModified_id());
        dto.setModified_at(entity.getModified_at());
        return dto;
    }
}
