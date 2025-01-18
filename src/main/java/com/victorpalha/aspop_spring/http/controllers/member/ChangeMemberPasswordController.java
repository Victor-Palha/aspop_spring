package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.dtos.ChangeMemberPasswordDTO;
import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.exceptions.InvalidPasswordError;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.useCases.ChangeMemberPasswordUseCase;
import com.victorpalha.aspop_spring.http.controllers.dtos.ChangeMemberPasswordRequestDTO;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class ChangeMemberPasswordController {
    private final ChangeMemberPasswordUseCase changeMemberPasswordUseCase;
    public ChangeMemberPasswordController(ChangeMemberPasswordUseCase changeMemberPasswordUseCase) {
        this.changeMemberPasswordUseCase = changeMemberPasswordUseCase;
    }

    @PatchMapping("/password")
    public ResponseEntity<Object> execute(HttpServletRequest req,
                                          @Valid @RequestBody ChangeMemberPasswordRequestDTO dto) {
        final String memberId = req.getAttribute("member_id").toString();
        try{
            ChangeMemberPasswordDTO changeMemberPasswordDTO = ChangeMemberPasswordDTO
                    .builder()
                    .memberId(memberId)
                    .oldPassword(dto.getOldPassword())
                    .newPassword(dto.getNewPassword())
                    .build();
            changeMemberPasswordUseCase.execute(changeMemberPasswordDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                    new ResponseMapper<>(HttpStatus.NO_CONTENT.value(), "Senha alterada com sucesso", null)
            );
        }
        catch (MemberNotFoundError e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseMapper<>(HttpStatus.NOT_FOUND.value(), e.getMessage(), null)
            );
        }
        catch (InvalidPasswordError e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseMapper<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), null)
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseMapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null)
            );
        }
    }
}
