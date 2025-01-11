package com.victorpalha.aspop_spring.domain.article.exceptions;

public class ArticleNotFoundError extends RuntimeException {
    public ArticleNotFoundError() {
        super("Artigo não encontrado");
    }
}
