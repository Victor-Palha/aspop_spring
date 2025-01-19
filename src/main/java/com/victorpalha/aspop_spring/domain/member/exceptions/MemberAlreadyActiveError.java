package com.victorpalha.aspop_spring.domain.member.exceptions;

public class MemberAlreadyActiveError extends RuntimeException {
    public MemberAlreadyActiveError() {
        super("O membro já está ativado!");
    }
}
