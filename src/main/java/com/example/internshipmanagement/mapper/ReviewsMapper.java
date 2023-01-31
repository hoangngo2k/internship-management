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
        entity.setDelFlg(dto.isDelFlg());
        entity.setCreateId(dto.getCreateId());
        entity.setCreateAt(dto.getCreateAt());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
        return entity;
    }

    public Reviews toEntity(Reviews entity, ReviewsDto dto) {
        entity.setType(dto.getType());
        entity.setReviewer(dto.getReviewer());
        entity.setObject(dto.getObject());
        entity.setRank(dto.getRank());
        entity.setContent(dto.getContent());
        entity.setDelFlg(dto.isDelFlg());
        entity.setModifiedId(dto.getModifiedId());
        entity.setModifiedAt(dto.getModifiedAt());
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
        dto.setDelFlg(entity.isDelFlg());
        dto.setCreateId(entity.getCreateId());
        dto.setCreateAt(entity.getCreateAt());
        dto.setModifiedId(entity.getModifiedId());
        dto.setModifiedAt(entity.getModifiedAt());
        return dto;
    }
}
