package com.example.proyecto_biblioteca.controller;

import com.example.proyecto_biblioteca.model.Loan;
import com.example.proyecto_biblioteca.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/search")
    public List<Loan> searchLoans(
            @RequestParam(name = "memberId", required = false) Integer memberId,
            @RequestParam(name = "memberName", required = false) String memberName) {
        if (memberId != null) {
            return loanService.getLoanHistoryByMemberId(memberId);
        } else if (memberName != null) {
            return loanService.getLoanHistoryByMemberName(memberName);
        } else {
            throw new IllegalArgumentException("Debe proporcionar un 'memberId' o un 'memberName' para buscar préstamos.");
        }
    }



    @PostMapping("/create")
    public Loan createLoan(@RequestParam int memberId, @RequestParam int bookId) {
        return loanService.createLoan(memberId, bookId);
    }

}
