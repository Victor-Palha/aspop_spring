package com.victorpalha.aspop_spring.domain.member.exceptions;

public class InvalidCredentialsError extends RuntimeException {
    public InvalidCredentialsError() {
        super("Ops, Credenciais inválidas!");
    }
}
