package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.CompanyCardDto;
import com.example.internshipmanagement.model.CompanyCard;

public class CompanyCardMapper {

    public CompanyCard toEntity(CompanyCardDto dto) {
        CompanyCard entity = new CompanyCard();
        entity.setId(dto.getId());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setUsing_flg(dto.isUsing_flg());
        entity.setCreate_id(dto.getCreate_id());
        entity.setCreate_at(dto.getCreate_at());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }

    public CompanyCard toEntity(CompanyCard entity, CompanyCardDto dto) {
        entity.setId(dto.getId());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setUsing_flg(dto.isUsing_flg());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }

    public CompanyCardDto toDto(CompanyCard entity) {
        CompanyCardDto dto = new CompanyCardDto();
        dto.setId(entity.getId());
        dto.setIs_del_flg(entity.isIs_del_flg());
        dto.setUsing_flg(entity.isUsing_flg());
        dto.setCreate_id(entity.getCreate_id());
        dto.setCreate_at(entity.getCreate_at());
        dto.setModified_id(entity.getModified_id());
        dto.setModified_at(entity.getModified_at());
        return dto;
    }
}
