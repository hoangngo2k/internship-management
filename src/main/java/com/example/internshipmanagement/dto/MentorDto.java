package com.example.internshipmanagement.dto;

public class MentorDto extends UserDto {

    private int maxInternship;
    private boolean isActiveFlg;

    public int getMaxInternship() {
        return maxInternship;
    }

    public void setMaxInternship(int maxInternship) {
        this.maxInternship = maxInternship;
    }

    public boolean isActiveFlg() {
        return isActiveFlg;
    }

    public void setActiveFlg(boolean activeFlg) {
        isActiveFlg = activeFlg;
    }
}
