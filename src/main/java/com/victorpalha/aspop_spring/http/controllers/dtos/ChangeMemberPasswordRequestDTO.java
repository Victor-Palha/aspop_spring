package com.victorpalha.aspop_spring.http.controllers.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChangeMemberPasswordRequestDTO {
    @Length(min = 6)
    private String oldPassword;
    @Length(min = 6)
    private String newPassword;
}
