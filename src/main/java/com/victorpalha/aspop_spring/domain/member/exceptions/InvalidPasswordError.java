package com.victorpalha.aspop_spring.domain.member.exceptions;

public class InvalidPasswordError extends RuntimeException {
    public InvalidPasswordError() {
        super("Senha antiga inválida!");
    }
}
