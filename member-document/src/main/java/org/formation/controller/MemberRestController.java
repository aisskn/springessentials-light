package org.formation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.formation.model.*;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.formation.service.MemberViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberRestController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DocumentRepository documentRepository;

    @JsonView({MemberViews.Simple.class})
    @GetMapping
    public List<Member> getMembers(@RequestParam Optional<String> keyword){
        if (keyword.isEmpty()){
        return memberRepository.findAll();
    }
        else {
            return memberRepository.findByNomContainsOrPrenomContainsAllIgnoreCase(keyword.get(),keyword.get());
        }
    }
    @JsonView({MemberViews.Details.class})
    @GetMapping("/{id}")
    public Member getMembersbyId(@PathVariable Long id){
        return memberRepository.fullLoad(id).orElseThrow(EntityNotFoundException::new);
        }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Member postMembers(@Valid @RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PutMapping
    public Member updateMembers(@Valid @RequestBody Member member) {
        memberRepository.findById(member.getId()).orElseThrow(EntityNotFoundException::new);
        return memberRepository.save(member);
    }

    @DeleteMapping("/{id}")
    public void deleteMembersbyId(@PathVariable Long id){
        memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        memberRepository.deleteById(id);
    }
    @GetMapping("/{id}/documents")
    public List<Document> getDocumentByMembers(@PathVariable Long id){
        memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return documentRepository.findByOwnerId(id);
    }

    @PostMapping("/{id}/documents")
    @ResponseStatus(HttpStatus.CREATED)
    public Member postMemberDocument(@Valid @RequestBody Document document, @PathVariable Long id) {
        Member member = memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        member.addDocument(document);
        return memberRepository.save(member);
    }
}

