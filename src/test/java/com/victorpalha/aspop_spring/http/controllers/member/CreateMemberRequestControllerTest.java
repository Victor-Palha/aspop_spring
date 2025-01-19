package com.victorpalha.aspop_spring.http.controllers.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
import com.victorpalha.aspop_spring.domain.member.dtos.CreateMemberRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class CreateMemberRequestControllerTest {
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_be_able_to_request_create_member() throws Exception {
        CreateMemberRequestDTO createMemberRequestDTO = CreateMemberRequestDTO.builder()
                .memberName("John Doe")
                .email("johndoe@example.com")
                .cpf("12345678901")
                .rg("1234567")
                .birthDate("2002-11-28")
                .motherName("Jane Doe")
                .fatherName("Jack Doe")
                .naturalness("New York")
                .maritalStatus(MaritalStatus.SOLTEIRO)
                .formation("Bachelor's in Computer Science")
                .workLocation("Main Office")
                .numberRegistration("EMP123456")
                .admissionDate("2022-04-07")
                .address("123 Main Street")
                .addressNumber("456")
                .cep("12345-678")
                .phoneNumber("+1 123 456 7890")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createMemberRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Requisição feita com sucesso!"));
    }
}
