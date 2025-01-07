package com.victorpalha.aspop_spring.domain.member.exceptions;

public class MemberIsNotActiveError extends RuntimeException {
    public MemberIsNotActiveError() {
        super("Membro não está ativo.");
    }
}
