package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChangeMemberProfileUseCaseTest {

    @InjectMocks
    private ChangeMemberProfileUseCase changeMemberProfileUseCase;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void should_update_member_profile() {
        String memberId = "123";
        CreateMemberRequestDTO changeMemberRequestDTO = CreateMemberRequestDTO.builder()
                .memberName("Updated Name")
                .email("updated@example.com")
                .cpf("98765432100")
                .rg("7654321")
                .birthDate("2002-11-28")
                .motherName("Updated Mother")
                .fatherName("Updated Father")
                .naturalness("Updated City")
                .maritalStatus(MaritalStatus.DIVORCIADO)
                .formation("Updated Degree")
                .workLocation("Updated Location")
                .admissionDate("2022-04-07")
                .address("Updated Street")
                .addressNumber("999")
                .numberRegistration("123456789")
                .cep("98765-432")
                .phoneNumber("+1 987 654 3210")
                .build();

        MemberEntity existingMember = new MemberEntity();
        existingMember.setMemberId(memberId);

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(existingMember));
        when(memberRepository.save(any(MemberEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MemberEntity updatedMember = changeMemberProfileUseCase.execute(memberId, changeMemberRequestDTO);
        System.out.println(updatedMember);
        assert updatedMember != null;
        assert updatedMember.getEmail().equals(changeMemberRequestDTO.getEmail());
        assert updatedMember.getMemberName().equals(changeMemberRequestDTO.getMemberName());
        assert updatedMember.getCpf().equals(changeMemberRequestDTO.getCpf());
        assert updatedMember.getRg().equals(changeMemberRequestDTO.getRg());
        assert updatedMember.getAddress().equals(changeMemberRequestDTO.getAddress());

        verify(memberRepository, times(1)).findById(memberId);
        verify(memberRepository, times(1)).save(updatedMember);
    }

    @Test
    public void should_throw_exception_when_member_not_found() {
        String memberId = "123";
        CreateMemberRequestDTO changeMemberRequestDTO = CreateMemberRequestDTO.builder()
                .memberName("Nonexistent Member")
                .build();

        when(memberRepository.findById(memberId)).thenReturn(Optional.empty());

        try {
            changeMemberProfileUseCase.execute(memberId, changeMemberRequestDTO);
        } catch (Exception e) {
            assert e instanceof MemberNotFoundError;
        }

        verify(memberRepository, times(1)).findById(memberId);
        verify(memberRepository, never()).save(any(MemberEntity.class));
    }
}
