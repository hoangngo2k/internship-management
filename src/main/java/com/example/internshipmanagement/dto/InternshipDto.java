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
    private String identify_card;
    private int status;
    private int level;
    private PositionDto position;
    private UniversityDto university;
    private CompanyCardDto company_card;

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getIdentify_card() {
        return identify_card;
    }

    public void setIdentify_card(String identify_card) {
        this.identify_card = identify_card;
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

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public UniversityDto getUniversity() {
        return university;
    }

    public void setUniversity(UniversityDto university) {
        this.university = university;
    }

    public CompanyCardDto getCompany_card() {
        return company_card;
    }

    public void setCompany_card(CompanyCardDto company_card) {
        this.company_card = company_card;
    }
}
