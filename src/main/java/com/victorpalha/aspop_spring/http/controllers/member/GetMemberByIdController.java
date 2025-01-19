package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.useCases.GetMemberByIdUseCase;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class GetMemberByIdController {
    private final GetMemberByIdUseCase getMemberByIdUseCase;
    public GetMemberByIdController(GetMemberByIdUseCase getMemberByIdUseCase) {
        this.getMemberByIdUseCase = getMemberByIdUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> execute(@PathVariable("id") String memberId) {
        try{
            MemberEntity memberEntity = getMemberByIdUseCase.execute(memberId);
            System.out.println(memberEntity);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMapper<>(HttpStatus.OK.value(), "Membro encontrado", memberEntity)
            );
        }
        catch (MemberNotFoundError e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMapper<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null)
            );
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseMapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null)
            );
        }
    }
}
