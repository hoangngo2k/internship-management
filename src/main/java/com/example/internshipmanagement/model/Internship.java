package com.example.internshipmanagement.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "internships")
public class Internship extends User {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Column(unique = true)
    private String identify_card;
    @Column(length = 1)
    private int status;
    @Column(length = 1)
    private int level;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
    @ManyToOne
    @JoinColumn(name = "company_card_id")
    private CompanyCard companyCard;
    @OneToMany(mappedBy = "internship")
    private List<InternshipTimeSheet> internshipTimeSheets;
    @OneToMany(mappedBy = "internship")
    private List<MentorInternship> mentorInternships;

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

    public CompanyCard getCompanyCard() {
        return companyCard;
    }

    public void setCompanyCard(CompanyCard companyCard) {
        this.companyCard = companyCard;
    }

    public List<InternshipTimeSheet> getInternshipTimeSheets() {
        return internshipTimeSheets;
    }

    public void setInternshipTimeSheets(List<InternshipTimeSheet> internshipTimeSheets) {
        this.internshipTimeSheets = internshipTimeSheets;
    }

    public List<MentorInternship> getMentorInternships() {
        return mentorInternships;
    }

    public void setMentorInternships(List<MentorInternship> mentorInternships) {
        this.mentorInternships = mentorInternships;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
