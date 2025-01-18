package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.useCases.FetchAllMembersUseCase;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class FetchAllMembersController {
    private final FetchAllMembersUseCase fetchAllMembersUseCase;
    public FetchAllMembersController(FetchAllMembersUseCase fetchAllMembersUseCase) {
        this.fetchAllMembersUseCase = fetchAllMembersUseCase;
    }

    @GetMapping("/")
    public ResponseEntity<Object> execute() {
        List<MemberEntity> members = fetchAllMembersUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMapper<>(HttpStatus.OK.value(), "Lista de membros", members)
        );
    }
}
