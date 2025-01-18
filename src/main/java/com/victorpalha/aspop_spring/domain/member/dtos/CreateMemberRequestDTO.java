package com.victorpalha.aspop_spring.domain.member.dtos;

import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequestDTO {
    @Length(min = 3)
    private String memberName;
    @Email()
    private String email;
    @Length(min = 11)
    private String cpf;
    @Length(min = 6)
    private String rg;
    private String birthDate;
    @Length(min = 1)
    private String motherName;
    private String fatherName;
    @Length(min = 1)
    private String naturalness;
    private MaritalStatus maritalStatus;
    @Length(min = 1)
    private String formation;
    @Length(min = 1)
    private String workLocation;
    @Length(min = 1)
    private String numberRegistration;
    private String admissionDate;
    @Length(min = 1)
    private String address;
    @Length(min = 1)
    private String addressNumber;
    @Length(min = 8)
    private String cep;
    @Length(min = 9)
    private String phoneNumber;

    public MemberEntity toEntity() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberName(memberName);
        memberEntity.setEmail(email);
        memberEntity.setCpf(cpf);
        memberEntity.setRg(rg);
        memberEntity.setBirthDate(birthDate);
        memberEntity.setMotherName(motherName);
        memberEntity.setFatherName(fatherName);
        memberEntity.setNaturalness(naturalness);
        memberEntity.setFormation(formation);
        memberEntity.setWorkLocation(workLocation);
        memberEntity.setNumberRegistration(numberRegistration);
        memberEntity.setAdmissionDate(admissionDate);
        memberEntity.setAddress(address);
        memberEntity.setAddressNumber(addressNumber);
        memberEntity.setCep(cep);
        memberEntity.setPhoneNumber(phoneNumber);
        return memberEntity;
    }
}
