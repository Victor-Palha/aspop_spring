package com.victorpalha.aspop_spring.domain.member.exceptions;

public class MemberWithSameCredentialsAlreadyExistsError extends RuntimeException {
    public MemberWithSameCredentialsAlreadyExistsError() {
        super("Membro com mesmas informações já existente.");
    }
}
