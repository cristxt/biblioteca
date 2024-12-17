package com.example.proyecto_biblioteca.repository;

import com.example.proyecto_biblioteca.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByMemberName(String name);
}
