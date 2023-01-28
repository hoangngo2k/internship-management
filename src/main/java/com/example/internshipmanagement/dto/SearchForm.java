package com.example.internshipmanagement.dto;

public class SearchForm {

    private String field;
    private String keyword;
    private boolean using_flg;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isUsing_flg() {
        return using_flg;
    }

    public void setUsing_flg(boolean using_flg) {
        this.using_flg = using_flg;
    }
}
