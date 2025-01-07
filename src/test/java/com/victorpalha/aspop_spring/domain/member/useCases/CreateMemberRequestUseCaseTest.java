package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberWithSameCredentialsAlreadyExistsError;
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
public class CreateMemberRequestUseCaseTest {

    @InjectMocks
    private CreateMemberRequestUseCase createMemberRequestUseCase;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void should_create_a_request_member(){
        CreateMemberRequestDTO CREATE_MEMBER_REQUEST_DTO = CreateMemberRequestDTO.builder()
                .memberName("John Doe")
                .email("johndoe@example.com")
                .cpf("12345678901")
                .rg("1234567")
                .birthDate(LocalDate.of(1990, 5, 15))
                .motherName("Jane Doe")
                .fatherName("Jack Doe")
                .naturalness("New York")
                .maritalStatus(MaritalStatus.SOLTEIRO)
                .formation("Bachelor's in Computer Science")
                .workLocation("Main Office")
                .numberRegistration("EMP123456")
                .admissionDate(LocalDate.of(2020, 1, 10))
                .address("123 Main Street")
                .addressNumber("456")
                .cep("12345-678")
                .phoneNumber("+1 123 456 7890")
                .build();
        MemberEntity memberEntity = CREATE_MEMBER_REQUEST_DTO.toEntity();

        when(memberRepository.findByEmailOrCpfOrRg(
                CREATE_MEMBER_REQUEST_DTO.getEmail(),
                CREATE_MEMBER_REQUEST_DTO.getCpf(),
                CREATE_MEMBER_REQUEST_DTO.getRg()
        )).thenReturn(Optional.empty());
        when(memberRepository.save(memberEntity)).thenReturn(memberEntity);

        try{
            MemberEntity responseExpected = createMemberRequestUseCase.execute(CREATE_MEMBER_REQUEST_DTO);
            System.out.println(responseExpected);
            assert responseExpected != null;
            assert responseExpected.getEmail().equals(CREATE_MEMBER_REQUEST_DTO.getEmail());
            assert responseExpected.getCpf().equals(CREATE_MEMBER_REQUEST_DTO.getCpf());
            assert responseExpected.getRg().equals(CREATE_MEMBER_REQUEST_DTO.getRg());
            assert responseExpected.getBirthDate().equals(CREATE_MEMBER_REQUEST_DTO.getBirthDate());
            assert responseExpected.getAdmissionDate().equals(CREATE_MEMBER_REQUEST_DTO.getAdmissionDate());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void should_throw_exception_when_member_with_same_credentials_exists() {
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

        MemberEntity existingMember = createMemberRequestDTO.toEntity();

        when(memberRepository.findByEmailOrCpfOrRg(
                createMemberRequestDTO.getEmail(),
                createMemberRequestDTO.getCpf(),
                createMemberRequestDTO.getRg()
        )).thenReturn(Optional.of(existingMember));

        try {
            createMemberRequestUseCase.execute(createMemberRequestDTO);
        } catch (Exception e) {
            assert e instanceof MemberWithSameCredentialsAlreadyExistsError;
        }
    }
}
