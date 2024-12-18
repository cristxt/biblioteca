package com.example.proyecto_biblioteca.repository;

import com.example.proyecto_biblioteca.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    List<Loan> findByMember_Id(int memberId);

    List<Loan> findByMember_MemberName(String memberName);

}


