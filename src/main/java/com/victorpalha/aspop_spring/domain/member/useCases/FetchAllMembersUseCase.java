package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fetch all members that are registered on the system
 * @author Victor Palha
 * @version 1.0
 * @since 07/01/25
 */
@Service
public class FetchAllMembersUseCase {

    private final MemberRepository memberRepository;

    public FetchAllMembersUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberEntity> execute() {
        return memberRepository.findAll();
    }
}
