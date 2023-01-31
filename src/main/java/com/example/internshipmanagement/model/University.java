package com.example.internshipmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "universities")
@EntityListeners(AuditingEntityListener.class)
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3)
    private String name;
    private boolean isDelFlg;
    @CreatedBy
    private int createId;
    @CreatedDate
    private Date createAt;
    @LastModifiedBy
    private int modifiedId;
    @LastModifiedDate
    private Date modifiedAt;
    @OneToMany(mappedBy = "university")
    private List<Internship> internshipList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDelFlg() {
        return isDelFlg;
    }

    public void setDelFlg(boolean delFlg) {
        isDelFlg = delFlg;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getModifiedId() {
        return modifiedId;
    }

    public void setModifiedId(int modifiedId) {
        this.modifiedId = modifiedId;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public List<Internship> getInternshipList() {
        return internshipList;
    }

    public void setInternshipList(List<Internship> internshipList) {
        this.internshipList = internshipList;
    }
}
