package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.dtos.ValidatedMemberResponseDTO;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;

/**
 * This use case is for when an Admin Member wants to validate an account
 * @author Victor Palha
 * @version 1.0
 * @since 07/01/2025
 */
@Service
public class ValidateMemberRequestUseCase {

    private final MemberRepository memberRepository;

    public ValidateMemberRequestUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public ValidatedMemberResponseDTO execute(String memberId) {
        Optional<MemberEntity> memberExists = memberRepository.findById(memberId);
        if (memberExists.isEmpty()) {
            throw new MemberNotFoundError();
        }
        String randomPassword = this.generatePassword();
        String passwordHash = new BCryptPasswordEncoder().encode(randomPassword);
        MemberEntity memberEntity = memberExists.get();
        memberEntity.setPassword(passwordHash);
        memberEntity.setActive(true);
        MemberEntity memberCreated = memberRepository.save(memberEntity);

        return ValidatedMemberResponseDTO
                .builder()
                .email(memberCreated.getEmail())
                .name(memberCreated.getMemberName())
                .password(randomPassword)
                .build();
    }

    private String generatePassword() {
        String[] VALID_CHARACTERS = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "@", "_", ".", "#", "&", "-", "*"
        }; // 66 characters
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            int randomNumber = random.nextInt(VALID_CHARACTERS.length - 1);
            password.append(VALID_CHARACTERS[randomNumber]);
        }

        return password.toString();
    }
}
