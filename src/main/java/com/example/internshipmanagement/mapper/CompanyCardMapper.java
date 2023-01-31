package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.CompanyCardDto;
import com.example.internshipmanagement.model.CompanyCard;

public class CompanyCardMapper {

    public CompanyCard toEntity(CompanyCardDto dto) {
        CompanyCard entity = new CompanyCard();
        entity.setId(dto.getId());
        entity.setDelFlg(dto.isDelFlg());
        entity.setUsingFlg(dto.isUsingFlg());
        entity.setCreateId(dto.getCreateId());
        entity.setCreateAt(dto.getCreateAt());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        return entity;
    }

    public CompanyCard toEntity(CompanyCard entity, CompanyCardDto dto) {
        entity.setId(dto.getId());
        entity.setDelFlg(dto.isDelFlg());
        entity.setUsingFlg(dto.isUsingFlg());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        return entity;
    }

    public CompanyCardDto toDto(CompanyCard entity) {
        CompanyCardDto dto = new CompanyCardDto();
        dto.setId(entity.getId());
        dto.setDelFlg(entity.isDelFlg());
        dto.setUsingFlg(entity.isUsingFlg());
        dto.setCreateId(entity.getCreateId());
        dto.setCreateAt(entity.getCreateAt());
        dto.setModifiedId(entity.getModifiedId());
        dto.setModifiedAt(entity.getModifiedAt());
        return dto;
    }
}
