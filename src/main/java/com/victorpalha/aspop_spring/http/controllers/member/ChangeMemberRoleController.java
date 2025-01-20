package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.exceptions.MemberIsNotActiveError;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.useCases.ChangeMemberRoleUseCase;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class ChangeMemberRoleController {
    private final ChangeMemberRoleUseCase changeMemberRoleUseCase;
    public ChangeMemberRoleController(ChangeMemberRoleUseCase changeMemberRoleUseCase) {
        this.changeMemberRoleUseCase = changeMemberRoleUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/role/{id}")
    public ResponseEntity<Object> execute(@PathVariable("id") String memberId){
        try{
            changeMemberRoleUseCase.execute(memberId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new ResponseMapper<>(HttpStatus.NO_CONTENT.value(), "Membro alterado com sucesso", null)
            );
        }
        catch (MemberNotFoundError e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMapper<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null)
            );
        }
        catch (MemberIsNotActiveError e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseMapper<>(HttpStatus.CONFLICT.value(), e.getMessage(), null)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseMapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null)
            );
        }
    }

}
