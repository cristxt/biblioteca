package com.example.proyecto_biblioteca.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name="members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String memberName;
    private String memberSurname;
    private String memberEmail;

    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH'h'")
    private Timestamp registrationDate;


    @Column(name = "Estado", columnDefinition = "tinyint(1) default 1")
    @JsonProperty("Estado")
    @JsonSerialize(using = ActiveStatusSerializer.class)
    private Boolean isActive = true;



    public Member(String memberName, String memberSurname, String memberEmail, Boolean isActive) {
        this.memberName = memberName;
        this.memberSurname = memberSurname;
        this.memberEmail = memberEmail;
        this.isActive = isActive;
    }

    public Member() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSurname() {
        return memberSurname;
    }

    public void setMemberSurname(String memberSurname) {
        this.memberSurname = memberSurname;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }


    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }
}

