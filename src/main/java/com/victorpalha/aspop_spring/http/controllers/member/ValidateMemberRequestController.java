package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.dtos.ValidatedMemberResponseDTO;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.useCases.ValidateMemberRequestUseCase;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import com.victorpalha.aspop_spring.providers.EmailProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class ValidateMemberRequestController {
    private final ValidateMemberRequestUseCase validateMemberRequestUseCase;
    private final EmailProvider emailProvider;
    public ValidateMemberRequestController(ValidateMemberRequestUseCase validateMemberRequestUseCase, EmailProvider emailProvider) {
        this.validateMemberRequestUseCase = validateMemberRequestUseCase;
        this.emailProvider = emailProvider;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/validate/{id}")
    public ResponseEntity<Object> execute(@PathVariable("id") String memberId){
        try{
            ValidatedMemberResponseDTO response = validateMemberRequestUseCase.execute(memberId);
            boolean wasEmailSend = emailProvider.sendEmail(
                    response.getEmail(),
                    response.getPassword(),
                    response.getName()
            );
            System.out.println(response);

            if (!wasEmailSend) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponseMapper<>(HttpStatus.CONFLICT.value(), "O Email não foi possível de ser enviado", null)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMapper<>(HttpStatus.OK.value(), "Membro validado com sucesso!", null)
            );
        }
        catch (MemberNotFoundError e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMapper<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseMapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null)
            );
        }
    }
}
