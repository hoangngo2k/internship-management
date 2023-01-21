package com.example.internshipmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_position")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPosition {

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
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;
}
