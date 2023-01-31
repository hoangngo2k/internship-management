package com.example.internshipmanagement.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "mentors")
public class Mentor extends User{

    private int maxInternship;
    private boolean isActiveFlg;
    @OneToMany(mappedBy = "mentor")
    private List<MentorInternship> mentorInternships;

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
