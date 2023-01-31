package com.example.internshipmanagement.dto;

import java.util.Date;

public class CompanyCardDto {
    private String id;
    private boolean usingFlg;
    private boolean isDelFlg;
    private int createId;
    private Date createAt;
    private int modifiedId;
    private Date modifiedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isUsingFlg() {
        return usingFlg;
    }

    public void setUsingFlg(boolean usingFlg) {
        this.usingFlg = usingFlg;
    }

    public boolean isDelFlg() {
        return isDelFlg;
    }

    public void setDelFlg(boolean delFlg) {
        isDelFlg = delFlg;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getModifiedId() {
        return modifiedId;
    }

    public void setModifiedId(int modifiedId) {
        this.modifiedId = modifiedId;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
