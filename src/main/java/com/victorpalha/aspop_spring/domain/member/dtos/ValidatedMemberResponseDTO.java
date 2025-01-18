package com.victorpalha.aspop_spring.domain.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidatedMemberResponseDTO {
    private String email;
    private String name;
    private String password;
}
