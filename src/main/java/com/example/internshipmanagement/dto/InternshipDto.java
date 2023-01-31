package com.example.internshipmanagement.dto;

import com.example.internshipmanagement.model.CompanyCard;
import com.example.internshipmanagement.model.Position;
import com.example.internshipmanagement.model.University;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class InternshipDto extends UserDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String identifyCard;
    private int status;
    private int level;
    private Position position;
    private University university;
    private CompanyCard companyCard;

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard;
    }

    public CompanyCard getCompanyCard() {
        return companyCard;
    }

    public void setCompanyCard(CompanyCard companyCard) {
        this.companyCard = companyCard;
    }
}
