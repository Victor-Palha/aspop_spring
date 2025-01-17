package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.InvalidCredentialsError;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateMemberUseCase {
    private final MemberRepository memberRepository;
    public AuthenticateMemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity execute(String cpf, String password) {
        Optional<MemberEntity> memberExists = memberRepository.findByCpf(cpf);
        if (memberExists.isEmpty()) {
            throw new InvalidCredentialsError();
        }
        boolean isPasswordMatch = new BCryptPasswordEncoder().matches(password, memberExists.get().getPassword());
        if(!isPasswordMatch) {
            throw new InvalidCredentialsError();
        }
        return memberExists.get();
    }
}
