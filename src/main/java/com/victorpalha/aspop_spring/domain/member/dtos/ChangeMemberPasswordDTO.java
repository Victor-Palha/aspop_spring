package com.victorpalha.aspop_spring.domain.member.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeMemberPasswordDTO {
    String memberId;
    String newPassword;
    String oldPassword;
}
