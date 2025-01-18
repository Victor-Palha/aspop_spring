package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This use case get information from member and changes on the database
 * @author Victor Palha
 * @version 1.0
 * @since 08/01/25
 */
@Service
public class ChangeMemberProfileUseCase {
    private final MemberRepository memberRepository;

    public ChangeMemberProfileUseCase(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity execute(String memberId, CreateMemberRequestDTO changeMemberRequestDTO) {
        Optional<MemberEntity> memberExists = memberRepository.findById(memberId);
        if (memberExists.isEmpty()) {
            throw new MemberNotFoundError();
        }
        MemberEntity memberUpdated = this.updateMember(memberExists.get(), changeMemberRequestDTO);

        memberRepository.save(memberUpdated);
        return memberUpdated;
    }

    private MemberEntity updateMember(MemberEntity memberEntity, CreateMemberRequestDTO changeMemberRequestDTO) {
        memberEntity.setMemberName(
                changeMemberRequestDTO.getMemberName() != null ? changeMemberRequestDTO.getMemberName() : memberEntity.getMemberName()
        );
        memberEntity.setEmail(
                changeMemberRequestDTO.getEmail() != null ? changeMemberRequestDTO.getEmail() : memberEntity.getEmail()
        );
        memberEntity.setCpf(
                changeMemberRequestDTO.getCpf() != null ? changeMemberRequestDTO.getCpf() : memberEntity.getCpf()
        );
        memberEntity.setRg(
                changeMemberRequestDTO.getRg() != null ? changeMemberRequestDTO.getRg() : memberEntity.getRg()
        );
        memberEntity.setBirthDate(
                changeMemberRequestDTO.getBirthDate() != null ? changeMemberRequestDTO.getBirthDate() : memberEntity.getBirthDate().toString()
        );
        memberEntity.setMotherName(
                changeMemberRequestDTO.getMotherName() != null ? changeMemberRequestDTO.getMotherName() : memberEntity.getMotherName()
        );
        memberEntity.setFatherName(
                changeMemberRequestDTO.getFatherName() != null ? changeMemberRequestDTO.getFatherName() : memberEntity.getFatherName()
        );
        memberEntity.setNaturalness(
                changeMemberRequestDTO.getNaturalness() != null ? changeMemberRequestDTO.getNaturalness() : memberEntity.getNaturalness()
        );
        memberEntity.setMaritalStatus(
                changeMemberRequestDTO.getMaritalStatus() != null ? changeMemberRequestDTO.getMaritalStatus() : memberEntity.getMaritalStatus()
        );
        memberEntity.setFormation(
                changeMemberRequestDTO.getFormation() != null ? changeMemberRequestDTO.getFormation() : memberEntity.getFormation()
        );
        memberEntity.setAdmissionDate(
                changeMemberRequestDTO.getAdmissionDate() != null ? changeMemberRequestDTO.getAdmissionDate() : memberEntity.getAdmissionDate().toString()
        );
        memberEntity.setCep(
                changeMemberRequestDTO.getCep() != null ? changeMemberRequestDTO.getCep() : memberEntity.getCep()
        );
        memberEntity.setAddress(
                changeMemberRequestDTO.getAddress() != null ? changeMemberRequestDTO.getAddress() : memberEntity.getAddress()
        );
        memberEntity.setAddressNumber(
                changeMemberRequestDTO.getAddressNumber() != null ? changeMemberRequestDTO.getAddressNumber() : memberEntity.getAddressNumber()
        );
        memberEntity.setPhoneNumber(
                changeMemberRequestDTO.getPhoneNumber() != null ? changeMemberRequestDTO.getPhoneNumber() : memberEntity.getPhoneNumber()
        );
        memberEntity.setWorkLocation(
                changeMemberRequestDTO.getWorkLocation() != null ? changeMemberRequestDTO.getWorkLocation() : memberEntity.getWorkLocation()
        );
        memberEntity.setNumberRegistration(
                changeMemberRequestDTO.getNumberRegistration() != null ? changeMemberRequestDTO.getNumberRegistration() : memberEntity.getNumberRegistration()
        );

        return memberEntity;
    }
}
