package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberWithSameCredentialsAlreadyExistsError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This use case is for when someone wants to register to the Aspop System as a Member
 * But until an admin user allows, the member request should be invalid
 * @author Victor Palha
 * @version 1.0
 * @since 07/01/25
 */
@Service
public class CreateMemberRequestUseCase {

    private final MemberRepository memberRepository;

    public CreateMemberRequestUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity execute(CreateMemberRequestDTO memberRequest){
        String memberRequestEmail = memberRequest.getEmail();
        String memberRequestCpf = memberRequest.getCpf();
        String memberRequestRg = memberRequest.getRg();
        Optional<MemberEntity> memberWithSameInformationExists = memberRepository.findByEmailOrCpfOrRg(
                memberRequestEmail,
                memberRequestCpf,
                memberRequestRg
        );

        if(memberWithSameInformationExists.isPresent()){
            throw new MemberWithSameCredentialsAlreadyExistsError();
        }

        MemberEntity memberToBeSave = memberRequest.toEntity();

        return memberRepository.save(memberToBeSave);
    }
}
