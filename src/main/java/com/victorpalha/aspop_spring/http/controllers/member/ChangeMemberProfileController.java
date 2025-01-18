package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.useCases.ChangeMemberProfileUseCase;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class ChangeMemberProfileController {
    private final ChangeMemberProfileUseCase changeMemberProfileUseCase;
    public ChangeMemberProfileController(ChangeMemberProfileUseCase changeMemberProfileUseCase) {
        this.changeMemberProfileUseCase = changeMemberProfileUseCase;
    }

    @PatchMapping("/profile")
    public ResponseEntity<Object> execute(HttpServletRequest req, @Valid @RequestBody CreateMemberRequestDTO dto) {
        final String memberId = req.getAttribute("member_id").toString();
        try{
            changeMemberProfileUseCase.execute(memberId, dto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMapper<>(HttpStatus.OK.value(), "Perfil alterado com sucesso", null)
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
