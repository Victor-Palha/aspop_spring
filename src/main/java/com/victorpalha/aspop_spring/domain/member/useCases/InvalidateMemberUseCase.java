package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberIsNotActiveError;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This use case invalidate a member then it'll not be allowed to access the system
 * @author Victor Palha
 * @version 1.0
 * @since 07/01/25
 */
@Service
public class InvalidateMemberUseCase {

    private final MemberRepository memberRepository;

    public InvalidateMemberUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity execute(String memberId){
        Optional<MemberEntity> memberExists = memberRepository.findById(memberId);
        if (memberExists.isEmpty()) {
            throw new MemberNotFoundError();
        }
        if(!memberExists.get().isActive()){
            throw new MemberIsNotActiveError();
        }
        MemberEntity updatedMember = memberExists.get();
        updatedMember.setActive(false);
        updatedMember.setPassword("");
        memberRepository.save(updatedMember);

        return updatedMember;
    }
}
