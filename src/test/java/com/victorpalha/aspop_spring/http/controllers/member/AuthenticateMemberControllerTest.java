package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
import com.victorpalha.aspop_spring.domain.member.constants.MemberRole;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AuthenticateMemberControllerTest {
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_authenticate_member() throws Exception {
        String password = new BCryptPasswordEncoder().encode("password123");
        MemberEntity mockMember = MemberEntity.builder()
                .memberName("John Doe")
                .email("jv.palha@gmail.com")
                .cpf("12345678900")
                .rg("1234567")
                .motherName("Jane Doe")
                .fatherName("Jack Doe")
                .naturalness("New York")
                .maritalStatus(MaritalStatus.SOLTEIRO)
                .formation("Bachelor's in Computer Science")
                .workLocation("Main Office")
                .numberRegistration("EMP123456")
                .address("123 Main Street")
                .addressNumber("456")
                .cep("12345-678")
                .phoneNumber("+1 123 456 7890")
                .build();
        mockMember.setBirthDate("2002-11-28");
        mockMember.setAdmissionDate("2022-04-07");
        mockMember.setRole(MemberRole.MEMBER);
        mockMember.setActive(true);
        mockMember.setPassword(password);
        mongoTemplate.save(mockMember);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "cpf": "12345678900",
                                        "password": "password123"
                                    }
                               """)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Autenticado com sucesso"))
                .andExpect(jsonPath("$.data.token").exists());
    }

    @Test
    public void should_not_authenticate_member_with_wrong_cpf() throws Exception {
        String password = new BCryptPasswordEncoder().encode("password123");
        MemberEntity mockMember = MemberEntity.builder()
                .memberName("John Doe")
                .email("jv.palha@gmail.com")
                .cpf("12345678900")
                .rg("1234567")
                .motherName("Jane Doe")
                .fatherName("Jack Doe")
                .naturalness("New York")
                .maritalStatus(MaritalStatus.SOLTEIRO)
                .formation("Bachelor's in Computer Science")
                .workLocation("Main Office")
                .numberRegistration("EMP123456")
                .address("123 Main Street")
                .addressNumber("456")
                .cep("12345-678")
                .phoneNumber("+1 123 456 7890")
                .build();
        mockMember.setBirthDate("2002-11-28");
        mockMember.setAdmissionDate("2022-04-07");
        mockMember.setRole(MemberRole.MEMBER);
        mockMember.setActive(true);
        mockMember.setPassword(password);
        mongoTemplate.save(mockMember);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "cpf": "12345678901",
                                        "password": "password123"
                                    }
                               """)
                )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Ops, Credenciais inválidas!"));
    }

    @Test
    public void should_not_authenticate_member_with_wrong_password() throws Exception {
        String password = new BCryptPasswordEncoder().encode("password123");
        MemberEntity mockMember = MemberEntity.builder()
                .memberName("John Doe")
                .email("jv.palha@gmail.com")
                .cpf("12345678900")
                .rg("1234567")
                .motherName("Jane Doe")
                .fatherName("Jack Doe")
                .naturalness("New York")
                .maritalStatus(MaritalStatus.SOLTEIRO)
                .formation("Bachelor's in Computer Science")
                .workLocation("Main Office")
                .numberRegistration("EMP123456")
                .address("123 Main Street")
                .addressNumber("456")
                .cep("12345-678")
                .phoneNumber("+1 123 456 7890")
                .build();
        mockMember.setBirthDate("2002-11-28");
        mockMember.setAdmissionDate("2022-04-07");
        mockMember.setRole(MemberRole.MEMBER);
        mockMember.setActive(true);
        mockMember.setPassword(password);
        mongoTemplate.save(mockMember);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "cpf": "12345678900",
                                        "password": "password1234"
                                    }
                               """)
                )
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Ops, Credenciais inválidas!"));
    }
}
