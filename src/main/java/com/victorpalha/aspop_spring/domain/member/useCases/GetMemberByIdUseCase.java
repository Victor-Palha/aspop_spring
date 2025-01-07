package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Get a unique member by his id
 * @author Victor Palha
 * @version 1.0
 * @since 07/01/25
 */
@Service
public class GetMemberByIdUseCase {

    private final MemberRepository memberRepository;

    public GetMemberByIdUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity execute(String memberId) {
        Optional<MemberEntity> memberExists = memberRepository.findById(memberId);
        if (memberExists.isEmpty()){
            throw new MemberNotFoundError();
        }

        return memberExists.get();
    }
}
