package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
import com.victorpalha.aspop_spring.domain.member.constants.MemberRole;
import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeMemberRoleUseCaseTest {

    @InjectMocks
    private ChangeMemberRoleUseCase changeMemberRoleUseCase;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void should_change_member_role_to_admin(){
        // Mock data
        String memberId = "12345";
        CreateMemberRequestDTO createMemberRequestDTO = CreateMemberRequestDTO.builder()
                .memberName("Jane Doe")
                .email("janedoe@example.com")
                .cpf("12345678901")
                .rg("9876543")
                .birthDate("2002-11-28")
                .motherName("Mary Doe")
                .fatherName("John Doe")
                .naturalness("California")
                .maritalStatus(MaritalStatus.CASADO)
                .formation("Master's in Business Administration")
                .workLocation("Branch Office")
                .numberRegistration("EMP654321")
                .admissionDate("2002-11-28")
                .address("456 Elm Street")
                .addressNumber("789")
                .cep("54321-123")
                .phoneNumber("+1 987 654 3210")
                .build();
        MemberEntity memberEntity = createMemberRequestDTO.toEntity();
        memberEntity.setMemberId(memberId);

        when(memberRepository.findById("12345")).thenReturn(Optional.of(memberEntity));

        try{
            MemberEntity result = changeMemberRoleUseCase.execute(memberId);
            assert (result != null);
            assert (result.getMemberId().equals(memberId));
            assert (result.getRole() == MemberRole.ADMIN);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void should_change_member_role_to_member(){
        // Mock data
        String memberId = "12345";
        CreateMemberRequestDTO createMemberRequestDTO = CreateMemberRequestDTO.builder()
                .memberName("Jane Doe")
                .email("janedoe@example.com")
                .cpf("12345678901")
                .rg("9876543")
                .birthDate("2002-11-28")
                .motherName("Mary Doe")
                .fatherName("John Doe")
                .naturalness("California")
                .maritalStatus(MaritalStatus.CASADO)
                .formation("Master's in Business Administration")
                .workLocation("Branch Office")
                .numberRegistration("EMP654321")
                .admissionDate("2002-11-28")
                .address("456 Elm Street")
                .addressNumber("789")
                .cep("54321-123")
                .phoneNumber("+1 987 654 3210")
                .build();
        MemberEntity memberEntity = createMemberRequestDTO.toEntity();
        memberEntity.setMemberId(memberId);
        memberEntity.setRole(MemberRole.ADMIN);

        when(memberRepository.findById("12345")).thenReturn(Optional.of(memberEntity));

        try{
            MemberEntity result = changeMemberRoleUseCase.execute(memberId);
            assert (result != null);
            assert (result.getMemberId().equals(memberId));
            assert (result.getRole() == MemberRole.MEMBER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void should_not_change_member_role_if_member_does_not_exist(){
        // Mock data
        String memberId = "12345";

        when(memberRepository.findById("12345")).thenReturn(Optional.empty());

        try{
            changeMemberRoleUseCase.execute(memberId);
        } catch (Exception e) {
            assert e instanceof MemberNotFoundError;
        }
    }
}
