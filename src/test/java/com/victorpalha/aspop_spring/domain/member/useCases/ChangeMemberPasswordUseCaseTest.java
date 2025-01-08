package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
import com.victorpalha.aspop_spring.domain.member.dtos.ChangeMemberPasswordDTO;
import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.InvalidPasswordError;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangeMemberPasswordUseCaseTest {
    @InjectMocks
    private ChangeMemberPasswordUseCase changeMemberPasswordUseCase;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void should_change_password() {
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
        String oldPassword = new BCryptPasswordEncoder().encode("password123");
        memberEntity.setPassword(oldPassword);

        when(memberRepository.findById("12345")).thenReturn(Optional.of(memberEntity));
        ChangeMemberPasswordDTO changeMemberPasswordDTO = ChangeMemberPasswordDTO
                .builder()
                .memberId(memberId)
                .oldPassword("password123")
                .newPassword("password12345")
                .build();
        MemberEntity memberChanged = changeMemberPasswordUseCase.execute(changeMemberPasswordDTO);

        assert memberChanged.getMemberId().equals(memberId);
        assert !Objects.equals(memberChanged.getPassword(), oldPassword);
    }

    @Test
    public void should_not_change_password_if_old_password_is_wrong() {
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
        String oldPassword = new BCryptPasswordEncoder().encode("password123");
        memberEntity.setPassword(oldPassword);

        when(memberRepository.findById("12345")).thenReturn(Optional.of(memberEntity));
        ChangeMemberPasswordDTO changeMemberPasswordDTO = ChangeMemberPasswordDTO
                .builder()
                .memberId(memberId)
                .oldPassword("password1234")
                .newPassword("password12345")
                .build();

        try{
            changeMemberPasswordUseCase.execute(changeMemberPasswordDTO);
        }catch (Exception e){
            assert e instanceof InvalidPasswordError;
        }
    }

    @Test
    public void should_not_change_password_if_member_does_not_exist() {
        // Mock data
        String memberId = "12345";
        when(memberRepository.findById("12345")).thenReturn(Optional.empty());
        ChangeMemberPasswordDTO changeMemberPasswordDTO = ChangeMemberPasswordDTO
                .builder()
                .memberId(memberId)
                .oldPassword("password1234")
                .newPassword("password12345")
                .build();

        try{
            changeMemberPasswordUseCase.execute(changeMemberPasswordDTO);
        }catch (Exception e){
            assert e instanceof MemberNotFoundError;
        }
    }
}
