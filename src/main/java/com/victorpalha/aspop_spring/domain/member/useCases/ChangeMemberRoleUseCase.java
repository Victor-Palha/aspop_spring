package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.constants.MemberRole;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberIsNotActiveError;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This use case change the member role by member ID
 * If member is MEMBER will be changed to ADMIN
 * If member is ADMIN will be changed to MEMBER
 * @author Victor Palha
 * @version 1.0
 * @since 08/01/25
 */
@Service
public class ChangeMemberRoleUseCase {

    private final MemberRepository memberRepository;
    public ChangeMemberRoleUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity execute(String memberId){
        Optional<MemberEntity> memberExists = memberRepository.findById(memberId);
        if (memberExists.isEmpty()) {
            throw new MemberNotFoundError();
        }
        if (!memberExists.get().isActive()) {
            throw new MemberIsNotActiveError();
        }
        MemberEntity memberToUpdate = this.updateMemberRole(memberExists.get());
        this.memberRepository.save(memberToUpdate);
        return memberToUpdate;
    }

    private MemberEntity updateMemberRole(MemberEntity member) {
        if(member.getRole() == MemberRole.MEMBER) {
            member.setRole(MemberRole.ADMIN);
            return member;
        }
        member.setRole(MemberRole.MEMBER);
        return member;
    }
}
