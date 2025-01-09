package com.victorpalha.aspop_spring.domain.news.exceptions;

public class NewsNotFoundError extends RuntimeException {
    public NewsNotFoundError() {
        super("Notícia não encontrada");
    }
}
