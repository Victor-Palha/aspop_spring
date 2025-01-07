package com.victorpalha.aspop_spring.domain.member.exceptions;

public class MemberNotFoundError extends RuntimeException {
    public MemberNotFoundError() {
        super("Membro não encontrado");
    }
}
