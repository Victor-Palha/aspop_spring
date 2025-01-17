package com.victorpalha.aspop_spring.http.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateMemberRequestDTO {
    @Length(min = 11)
    private String cpf;
    @Length(min = 6)
    private String password;
}
