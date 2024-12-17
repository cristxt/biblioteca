package com.example.proyecto_biblioteca.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_surname")
    private String memberSurname;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 1")
    private Boolean isActive = true;


    public Member(String memberName, String memberSurname, String memberEmail, LocalDate registrationDate, Boolean isActive) {
        this.memberName = memberName;
        this.memberSurname = memberSurname;
        this.memberEmail = memberEmail;
        this.registrationDate = registrationDate;
        this.isActive = isActive;
    }

    public Member(){
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
