package com.victorpalha.aspop_spring.domain.member.entities;

import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
import com.victorpalha.aspop_spring.domain.member.constants.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.Email;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "members")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    @Id
    private String memberId;
    @Length(min = 3)
    private String memberName;
    @Email()
    private String email;
    private String password = "";
    @Length(min = 11)
    private String cpf;
    @Length(min = 6)
    private String rg;
    private LocalDate birthDate;
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
    private LocalDate admissionDate;
    @Length(min = 1)
    private String address;
    @Length(min = 1)
    private String addressNumber;
    @Length(min = 6)
    private String cep;
    @Length(min = 9)
    private String phoneNumber;
    private MemberRole role = MemberRole.MEMBER;
    private boolean isActive = false;
}