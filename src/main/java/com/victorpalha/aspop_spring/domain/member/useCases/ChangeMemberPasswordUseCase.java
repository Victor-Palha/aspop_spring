package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.dtos.ChangeMemberPasswordDTO;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.InvalidPasswordError;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This use case will check the current password from the member
 * If matches, will accept a new one to be saved on the database
 * @author Victor Palha
 * @version 1.0
 * @since 08/01/25
 */
@Service
public class ChangeMemberPasswordUseCase {
    private final MemberRepository memberRepository;

    public ChangeMemberPasswordUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity execute(ChangeMemberPasswordDTO changeMemberPasswordDTO) {
        Optional<MemberEntity> memberExists = memberRepository.findById(changeMemberPasswordDTO.getMemberId());
        if (memberExists.isEmpty()) {
            throw new MemberNotFoundError();
        }
        MemberEntity memberToChange = memberExists.get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isOldPasswordMatched = passwordEncoder.matches(changeMemberPasswordDTO.getOldPassword(), memberToChange.getPassword());
        if (!isOldPasswordMatched) {
            throw new InvalidPasswordError();
        }
        String newPassword = passwordEncoder.encode(changeMemberPasswordDTO.getNewPassword());
        memberToChange.setPassword(newPassword);
        memberRepository.save(memberToChange);
        return memberToChange;
    }
}
