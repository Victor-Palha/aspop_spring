package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.useCases.DeleteMemberUseCase;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class DeleteMemberController {
    private final DeleteMemberUseCase deleteMemberUseCase;

    public DeleteMemberController(DeleteMemberUseCase deleteMemberUseCase) {
        this.deleteMemberUseCase = deleteMemberUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public ResponseEntity<Object> execute(@PathVariable("id") String memberId) {
        try {
            deleteMemberUseCase.execute(memberId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMapper<>(HttpStatus.OK.value(), "Membro deletado com sucesso!", null)
            );
        } catch (MemberNotFoundError e) {
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
