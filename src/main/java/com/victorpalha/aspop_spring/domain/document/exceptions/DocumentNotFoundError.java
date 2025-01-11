package com.victorpalha.aspop_spring.domain.document.exceptions;

public class DocumentNotFoundError extends RuntimeException {
    public DocumentNotFoundError() {
        super("Documento não encontrado");
    }
}
