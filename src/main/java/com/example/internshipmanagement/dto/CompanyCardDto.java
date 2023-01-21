package com.example.internshipmanagement.dto;

import java.util.Date;

public class CompanyCardDto {
    private String id;
    private boolean using_flg;
    private boolean is_del_flg;
    private int create_id;
    private Date create_at;
    private int modified_id;
    private Date modified_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isUsing_flg() {
        return using_flg;
    }

    public void setUsing_flg(boolean using_flg) {
        this.using_flg = using_flg;
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
