package com.victorpalha.aspop_spring.domain.member.entities;

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
    @Length(min = 11)
    private String password = "";
    private String cpf;
    @Length(min = 6)
    private String rg;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String naturalness;
    private String maritalStatus;
    private String formation;
    private String workLocation;
    private String numberRegistration;
    private LocalDate admissionDate;
    private String address;
    private String addressNumber;
    private String cep;
    private String phoneNumber;
    private MemberRole role = MemberRole.MEMBER;
    private boolean isActive = false;
}