package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberWithSameCredentialsAlreadyExistsError;
import com.victorpalha.aspop_spring.domain.member.useCases.CreateMemberRequestUseCase;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class CreateMemberRequestController {
    private final CreateMemberRequestUseCase createMemberRequestUseCase;

    public CreateMemberRequestController(CreateMemberRequestUseCase createMemberRequestUseCase) {
        this.createMemberRequestUseCase = createMemberRequestUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> execute(@RequestBody CreateMemberRequestDTO createMemberRequestDTO) {
        try{
            createMemberRequestUseCase.execute(createMemberRequestDTO);
            return ResponseEntity.status(201).body(
                    new ResponseMapper<>(201, "Requisição feita com sucesso!", null)
            );
        }
        catch (Exception e) {
            if(e instanceof MemberWithSameCredentialsAlreadyExistsError){
                return ResponseEntity.status(409).body(
                        new ResponseMapper<>(201, e.getMessage(), null)
                );
            }
            return ResponseEntity.status(500).body(
                    new ResponseMapper<>(500, e.getMessage(), null)
            );
        }
    }
}
