package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.InternshipTimeSheetDto;
import com.example.internshipmanagement.model.InternshipTimeSheet;

public class InternshipTimeSheetMapper {

    public InternshipTimeSheet toEntity(InternshipTimeSheetDto dto) {
        InternshipTimeSheet entity = new InternshipTimeSheet();
        entity.setTime(dto.getTime());
        entity.setWorking_day(dto.getWorking_day());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setCreate_id(dto.getCreate_id());
        entity.setCreate_at(dto.getCreate_at());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }

    public InternshipTimeSheet toEntity(InternshipTimeSheet entity, InternshipTimeSheetDto dto) {
        entity.setTime(dto.getTime());
        entity.setWorking_day(dto.getWorking_day());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }

    public InternshipTimeSheetDto toDto(InternshipTimeSheet entity) {
        InternshipTimeSheetDto dto = new InternshipTimeSheetDto();
        dto.setId(entity.getId());
        dto.setTime(entity.getTime());
        dto.setWorking_day(entity.getWorking_day());
        dto.setIs_del_flg(entity.isIs_del_flg());
        dto.setCreate_id(entity.getCreate_id());
        dto.setCreate_at(entity.getCreate_at());
        dto.setModified_id(entity.getModified_id());
        dto.setModified_at(entity.getModified_at());
        return dto;
    }
}
