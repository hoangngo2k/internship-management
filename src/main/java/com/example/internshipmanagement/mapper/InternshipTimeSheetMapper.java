package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.InternshipTimeSheetDto;
import com.example.internshipmanagement.model.InternshipTimeSheet;

public class InternshipTimeSheetMapper {

    public InternshipTimeSheet toEntity(InternshipTimeSheetDto dto) {
        InternshipTimeSheet entity = new InternshipTimeSheet();
        entity.setTime(dto.getTime());
        entity.setWorkingDay(dto.getWorkingDay());
        entity.setDelFlg(dto.isDelFlg());
        entity.setCreateId(dto.getCreateId());
        entity.setCreateAt(dto.getCreateAt());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        entity.setInternship(dto.getInternship());
        return entity;
    }

    public InternshipTimeSheet toEntity(InternshipTimeSheet entity, InternshipTimeSheetDto dto) {
        entity.setTime(dto.getTime());
        entity.setWorkingDay(dto.getWorkingDay());
        entity.setDelFlg(dto.isDelFlg());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        entity.setInternship(dto.getInternship());
        return entity;
    }

    public InternshipTimeSheetDto toDto(InternshipTimeSheet entity) {
        InternshipTimeSheetDto dto = new InternshipTimeSheetDto();
        dto.setId(entity.getId());
        dto.setTime(entity.getTime());
        dto.setWorkingDay(entity.getWorkingDay());
        dto.setDelFlg(entity.isDelFlg());
        dto.setCreateId(entity.getCreateId());
        dto.setCreateAt(entity.getCreateAt());
        dto.setModifiedId(entity.getModifiedId());
        dto.setModifiedAt(entity.getModifiedAt());
        dto.setInternship(entity.getInternship());
        return dto;
    }
}
