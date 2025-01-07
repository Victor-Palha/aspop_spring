package com.victorpalha.aspop_spring.domain.member.exceptions;

public class MemberWithSameCredentialsAlreadyExistsError extends RuntimeException {
    public MemberWithSameCredentialsAlreadyExistsError() {
        super("Member with same credentials already exists");
    }
}
