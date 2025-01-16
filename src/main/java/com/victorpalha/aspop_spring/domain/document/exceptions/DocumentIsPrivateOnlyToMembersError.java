package com.victorpalha.aspop_spring.domain.document.exceptions;

public class DocumentIsPrivateOnlyToMembersError extends RuntimeException {
    public DocumentIsPrivateOnlyToMembersError() {
        super("Este documento é acessivel apenas para membros!");
    }
}
