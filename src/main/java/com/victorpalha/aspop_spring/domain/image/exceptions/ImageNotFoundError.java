package com.victorpalha.aspop_spring.domain.image.exceptions;

public class ImageNotFoundError extends RuntimeException {
    public ImageNotFoundError() {
        super("Imagem não encontrada");
    }
}
