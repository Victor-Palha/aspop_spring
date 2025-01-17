package com.victorpalha.aspop_spring.http.mappers;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseMapper<T> {
    // Getters e Setters
    private int status;
    private String message;
    private T data;

    public ResponseMapper(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
