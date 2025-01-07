package com.victorpalha.aspop_spring;

import org.springframework.boot.SpringApplication;

public class TestAspopSpringApplication {

    public static void main(String[] args) {
        SpringApplication.from(AspopSpringApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
