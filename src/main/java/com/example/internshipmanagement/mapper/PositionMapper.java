package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.PositionDto;
import com.example.internshipmanagement.model.Position;

public class PositionMapper {

    public Position toEntity(PositionDto dto) {
        Position entity = new Position();
        entity.setName(dto.getName());
        entity.setDelFlg(dto.isDelFlg());
        entity.setCreateId(dto.getCreateId());
        entity.setCreateAt(dto.getCreateAt());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        return entity;
    }

    public Position toEntity(Position entity, PositionDto dto) {
        entity.setName(dto.getName());
        entity.setDelFlg(dto.isDelFlg());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        return entity;
    }

    public PositionDto toDto(Position entity) {
        PositionDto dto = new PositionDto();
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
