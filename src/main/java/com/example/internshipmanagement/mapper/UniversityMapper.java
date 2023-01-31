package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.PositionDto;
import com.example.internshipmanagement.dto.UniversityDto;
import com.example.internshipmanagement.model.Position;
import com.example.internshipmanagement.model.University;

public class UniversityMapper {

    public University toEntity(UniversityDto dto) {
        University entity = new University();
        entity.setName(dto.getName());
        entity.setDelFlg(dto.isDelFlg());
        entity.setCreateId(dto.getCreateId());
        entity.setCreateAt(dto.getCreateAt());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        return entity;
    }

    public University toEntity(University entity, UniversityDto dto) {
        entity.setName(dto.getName());
        entity.setDelFlg(dto.isDelFlg());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        return entity;
    }

    public UniversityDto toDto(University entity) {
        UniversityDto dto = new UniversityDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDelFlg(entity.isDelFlg());
        dto.setCreateId(entity.getCreateId());
        dto.setCreateAt(entity.getCreateAt());
        dto.setModifiedId(entity.getModifiedId());
        dto.setModifiedAt(entity.getModifiedAt());
        return dto;
    }
}
