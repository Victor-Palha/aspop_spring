package com.victorpalha.aspop_spring.http.controllers.member;

import com.victorpalha.aspop_spring.domain.member.constants.MaritalStatus;
import com.victorpalha.aspop_spring.domain.member.constants.MemberRole;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.providers.JWTProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class InvalidateMemberControllerTest {
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

    @Autowired
    private JWTProvider jwtProvider;

    @Test
    public void should_invalidate_member() throws Exception {
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

        MemberEntity memberMock = mongoTemplate.save(mockMember);
        String jwtToken = jwtProvider.generateMainToken(
                "123123",
                MemberRole.ADMIN,
                Instant.now().plusSeconds(60)
        );

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member/invalidate/".concat(memberMock.getMemberId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwtToken)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Membro foi invalidado com sucesso!"));
    }

    @Test
    public void should_not_invalidate_member_if_is_already_inactive() throws Exception {
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
        mockMember.setActive(false);

        MemberEntity memberMock = mongoTemplate.save(mockMember);
        String jwtToken = jwtProvider.generateMainToken(
                "123123",
                MemberRole.ADMIN,
                Instant.now().plusSeconds(60)
        );

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member/invalidate/".concat(memberMock.getMemberId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwtToken)
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Membro não está ativo."));
    }

    @Test
    public void should_not_invalidate_member_if_doesnt_exists() throws Exception {

        String jwtToken = jwtProvider.generateMainToken(
                "123123",
                MemberRole.ADMIN,
                Instant.now().plusSeconds(60)
        );

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/member/invalidate/".concat("banana"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwtToken)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Membro não encontrado"));
    }

}
