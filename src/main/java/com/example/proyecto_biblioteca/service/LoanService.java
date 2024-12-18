package com.example.proyecto_biblioteca.service;

import com.example.proyecto_biblioteca.model.Book;
import com.example.proyecto_biblioteca.model.Loan;
import com.example.proyecto_biblioteca.model.Member;
import com.example.proyecto_biblioteca.repository.BookRepository;
import com.example.proyecto_biblioteca.repository.LoanRepository;
import com.example.proyecto_biblioteca.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, MemberRepository memberRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
    }

    public List<Loan> getLoanHistoryByMemberId(int memberId) {
        return loanRepository.findByMember_Id(memberId);
    }

    public List<Loan> getLoanHistoryByMemberName(String memberName) {
        return loanRepository.findByMember_MemberName(memberName);
    }


    public Loan createLoan(int memberId, int bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(loan.getLoanDate().plusWeeks(2));

        return loanRepository.save(loan);
    }
}

