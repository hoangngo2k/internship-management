package com.example.internshipmanagement.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "mentors")
public class Mentor extends User{

    private int max_internship;
    private boolean is_active_flg;
    @OneToMany(mappedBy = "mentor")
    private List<MentorInternship> mentorInternships;

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
