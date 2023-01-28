package com.example.internshipmanagement.dto;

import java.util.Date;

public class ReviewsDto {
    private Long id;
    private int type;
    private String reviewer;
    private String object;
    private String rank;
    private String content;
    private boolean is_del_flg;
    private int create_id;
    private Date create_at;
    private int modified_id;
    private Date modified_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIs_del_flg() {
        return is_del_flg;
    }

    public void setIs_del_flg(boolean is_del_flg) {
        this.is_del_flg = is_del_flg;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public int getModified_id() {
        return modified_id;
    }

    public void setModified_id(int modified_id) {
        this.modified_id = modified_id;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }
}
