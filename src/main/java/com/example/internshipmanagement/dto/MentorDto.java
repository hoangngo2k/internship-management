package com.example.internshipmanagement.dto;

public class MentorDto extends UserDto {

    private int max_internship;
    private boolean is_active_flg;

    public int getMax_internship() {
        return max_internship;
    }

    public void setMax_internship(int max_internship) {
        this.max_internship = max_internship;
    }

    public boolean isIs_active_flg() {
        return is_active_flg;
    }

    public void setIs_active_flg(boolean is_active_flg) {
        this.is_active_flg = is_active_flg;
    }
}
