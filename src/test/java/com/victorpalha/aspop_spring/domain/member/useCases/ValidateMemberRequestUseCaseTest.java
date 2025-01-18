package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.dtos.ValidatedMemberResponseDTO;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ValidateMemberRequestUseCaseTest {
    @InjectMocks
    private ValidateMemberRequestUseCase validateMemberRequestUseCase;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void should_validate_an_member() {
        // Mock data
        String memberId = "12345";
        CreateMemberRequestDTO createMemberRequestDTO = CreateMemberRequestDTO.builder()
                .memberName("Jane Doe")
                .email("janedoe@example.com")
                .cpf("12345678901")
                .rg("9876543")
                .birthDate(LocalDate.of(1985, 3, 20))
                .motherName("Mary Doe")
                .fatherName("John Doe")
                .naturalness("California")
                .maritalStatus(MaritalStatus.CASADO)
                .formation("Master's in Business Administration")
                .workLocation("Branch Office")
                .numberRegistration("EMP654321")
                .admissionDate(LocalDate.of(2018, 6, 15))
                .address("456 Elm Street")
                .addressNumber("789")
                .cep("54321-123")
                .phoneNumber("+1 987 654 3210")
                .build();
        MemberEntity memberEntity = createMemberRequestDTO.toEntity();
        memberEntity.setMemberId(memberId);

        // Mock behavior
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(memberEntity));
        when(memberRepository.save(any(MemberEntity.class))).thenReturn(memberEntity);

        // Execute use case
        ValidatedMemberResponseDTO validatedMember = validateMemberRequestUseCase.execute(memberId);
        // Assertions
        assert validatedMember != null;
        assert validatedMember.getPassword() != null;
        assert validatedMember.getPassword().length() == 10;
        assert validatedMember.getEmail().equals(memberEntity.getEmail());
        assert validatedMember.getName().equals(memberEntity.getMemberName());
    }

    @Test
    public void should_throw_member_not_found_error_when_member_does_not_exist() {
        // Mock data
        String memberId = "12345";

        // Mock behavior
        when(memberRepository.findById(memberId)).thenReturn(Optional.empty());

        try{
            validateMemberRequestUseCase.execute(memberId);
        }catch (Exception e) {
            assert e instanceof MemberNotFoundError;
        }
    }
}
