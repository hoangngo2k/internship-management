package com.example.internshipmanagement.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mentor_internship")
@Data
public class MentorInternship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean is_del_flg;
    @CreatedBy
    private int create_id;
    @CreatedDate
    private Date create_at;
    @LastModifiedBy
    private int modified_id;
    @LastModifiedDate
    private Date modified_at;
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
    @ManyToOne
    @JoinColumn(name = "internship_id")
    private Internship internship;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
}
