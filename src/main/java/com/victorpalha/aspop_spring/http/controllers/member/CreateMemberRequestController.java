package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberWithSameCredentialsAlreadyExistsError;
import com.victorpalha.aspop_spring.domain.member.useCases.CreateMemberRequestUseCase;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@Validated
public class CreateMemberRequestController {

    private final CreateMemberRequestUseCase createMemberRequestUseCase;

    public CreateMemberRequestController(CreateMemberRequestUseCase createMemberRequestUseCase) {
        this.createMemberRequestUseCase = createMemberRequestUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> execute(@Valid @RequestBody CreateMemberRequestDTO createMemberRequestDTO) {
        try {
            createMemberRequestUseCase.execute(createMemberRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseMapper<>(HttpStatus.CREATED.value(), "Requisição feita com sucesso!", null)
            );
        } catch (MemberWithSameCredentialsAlreadyExistsError e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseMapper<>(HttpStatus.CONFLICT.value(), e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseMapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null)
            );
        }
    }
}
