package com.victorpalha.aspop_spring.http.controllers.dtos;

import lombok.Data;

@Data
public class ChangeMemberPasswordRequestDTO {
    private String oldPassword;
    private String newPassword;
}
