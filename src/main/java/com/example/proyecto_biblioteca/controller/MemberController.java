package com.example.proyecto_biblioteca.controller;

import com.example.proyecto_biblioteca.model.Member;
import com.example.proyecto_biblioteca.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Encontrar miembros
    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAll();
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> findMemberById(@PathVariable int id) {
        return memberService.findMember(id)
                .map(member -> new ResponseEntity<>(member, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/members/name/{name}")
    public ResponseEntity<Member> findMemberByName(@PathVariable String name) {
        return memberService.findMember(name)
                .map(member -> new ResponseEntity<>(member, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear miembros
    @PostMapping("/members")
    public ResponseEntity<List<Member>> createMembers(@RequestBody List<Member> newMembers) {
        List<Member> savedMembers = memberService.addMembers(newMembers);
        return new ResponseEntity<>(savedMembers, HttpStatus.CREATED);
    }

    // Actualizar miembros
    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable int id, @RequestBody Member member) {
        return memberService.findMember(id)
                .map(foundMember -> {
                    Member updated = memberService.updatedMember(id, member);
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar miembros
    @DeleteMapping("/members")
    public ResponseEntity<Void> deleteMembersById(@RequestBody List<Integer> id) {
        memberService.deleteMembersById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
