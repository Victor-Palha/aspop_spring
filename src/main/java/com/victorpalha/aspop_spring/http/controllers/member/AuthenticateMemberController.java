package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.InvalidCredentialsError;
import com.victorpalha.aspop_spring.domain.member.useCases.AuthenticateMemberUseCase;
import com.victorpalha.aspop_spring.http.controllers.dtos.AuthenticateMemberRequestDTO;
import com.victorpalha.aspop_spring.http.mappers.ResponseMapper;
import com.victorpalha.aspop_spring.providers.JWTProvider;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@Validated
public class AuthenticateMemberController {
    private final AuthenticateMemberUseCase authenticateMemberUseCase;
    private final JWTProvider jwtProvider;
    public AuthenticateMemberController(AuthenticateMemberUseCase authenticateMemberUseCase, JWTProvider jwtProvider) {
        this.authenticateMemberUseCase = authenticateMemberUseCase;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> execute(@Valid @RequestBody AuthenticateMemberRequestDTO request) {
        try{
            MemberEntity member = authenticateMemberUseCase.execute(request.getCpf(), request.getPassword());
            Instant expirationTime = Instant.now().plus(Duration.ofDays(7));
            String jwtToken = this.jwtProvider.generateMainToken(
                    member.getMemberId(),
                    member.getRole(),
                    expirationTime
            );
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtToken);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseMapper<>(HttpStatus.OK.value(), "Autenticado com sucesso", response)
            );
        }
        catch (InvalidCredentialsError e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ResponseMapper<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null)
            );
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseMapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null)
            );
        }
    }
}
