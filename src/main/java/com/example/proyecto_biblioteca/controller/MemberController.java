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
    //encontrar miembros
    @GetMapping("/members")
    public List<Member> getAllMembers(){
        return memberService.getAll();
    }
    @GetMapping("/members/{id}")
    public ResponseEntity<Object> findMemberById(@PathVariable int id) {
        Optional<Member> foundMember = memberService.findMember(id);

        if (foundMember.isPresent()) {
            return new ResponseEntity<>(foundMember.get(), HttpStatus.FOUND);
        }

        return new ResponseEntity<>("Miembro no encontrado", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/members/name/{name}")
    public ResponseEntity<Object> findMemberByName(@PathVariable String name) {
        Optional<Member> foundMember = memberService.findMember(name);

        if (foundMember.isPresent()) {
            return new ResponseEntity<>(foundMember.get(), HttpStatus.FOUND);
        }

        return new ResponseEntity<>("Miembro no encontrado", HttpStatus.NOT_FOUND);
    }
    //crear miembros

    @PostMapping("/members")
    public ResponseEntity<List<Member>> createMembers(@RequestBody List<Member> newMembers) {
        List<Member> savedMembers = memberService.addMembers(newMembers);
        return new ResponseEntity<>(savedMembers, HttpStatus.CREATED);
    }

    //update miembros

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> Member(@PathVariable int id, @RequestBody Member Member) {
        Optional<Member> foundMember = memberService.findMember(id);

        if (foundMember.isPresent()) {
            Member updated = memberService.updatedMember(id, Member);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //eliminar miembros
    @DeleteMapping("/members")
    public ResponseEntity<String> deleteMembersById(@RequestBody List<Integer> id) {
        try {
            memberService.deleteMembersById(id);
            return new ResponseEntity<>("Producto(s) eliminado(s) correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto", HttpStatus.BAD_REQUEST);
        }
    }

}


