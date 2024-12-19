package com.example.proyecto_biblioteca.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="members")
@Getter
@Setter
@NoArgsConstructor

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Loan> loans;

    public Member(String memberName, String memberSurname, String memberEmail, Boolean isActive) {
        this.memberName = memberName;
        this.memberSurname = memberSurname;
        this.memberEmail = memberEmail;
        this.isActive = isActive;
    }

}





