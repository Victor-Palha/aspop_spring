package com.victorpalha.aspop_spring.domain.event.exceptions;

public class EventNotFoundError extends RuntimeException {
    public EventNotFoundError() {
        super("Evento não encontrado");
    }
}
