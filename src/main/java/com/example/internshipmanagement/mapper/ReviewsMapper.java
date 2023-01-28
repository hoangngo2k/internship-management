package com.example.internshipmanagement.mapper;

import com.example.internshipmanagement.dto.ReviewsDto;
import com.example.internshipmanagement.model.Reviews;

public class ReviewsMapper {

    public Reviews toEntity(ReviewsDto dto) {
        Reviews entity = new Reviews();
        entity.setType(dto.getType());
        entity.setReviewer(dto.getReviewer());
        entity.setObject(dto.getObject());
        entity.setRank(dto.getRank());
        entity.setContent(dto.getContent());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setCreate_id(dto.getCreate_id());
        entity.setCreate_at(dto.getCreate_at());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }

    public Reviews toEntity(Reviews entity, ReviewsDto dto) {
        entity.setType(dto.getType());
        entity.setReviewer(dto.getReviewer());
        entity.setObject(dto.getObject());
        entity.setRank(dto.getRank());
        entity.setContent(dto.getContent());
        entity.setIs_del_flg(dto.isIs_del_flg());
        entity.setModified_id(dto.getModified_id());
        entity.setModified_at(dto.getModified_at());
        return entity;
    }

    public ReviewsDto toDto(Reviews entity) {
        ReviewsDto dto = new ReviewsDto();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setReviewer(entity.getReviewer());
        dto.setObject(entity.getObject());
        dto.setRank(entity.getRank());
        dto.setContent(entity.getContent());
        dto.setIs_del_flg(entity.isIs_del_flg());
        dto.setCreate_id(entity.getCreate_id());
        dto.setCreate_at(entity.getCreate_at());
        dto.setModified_id(entity.getModified_id());
        dto.setModified_at(entity.getModified_at());
        return dto;
    }
}
