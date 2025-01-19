package com.victorpalha.aspop_spring.http.controllers.member;

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
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class FetchAllMembersControllerTest {
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
    public void should_fetch_all_members() throws Exception {
        MemberEntity member_1 = new MemberEntity();
        member_1.setMemberName("John Doe");
        MemberEntity member_2 = new MemberEntity();
        member_2.setMemberName("Jane Doe");
        MemberEntity member_3 = new MemberEntity();
        member_3.setMemberName("James Doe");
        List<MemberEntity> members = Arrays.asList(member_1, member_2, member_3);
        mongoTemplate.insertAll(members);
        String jwtToken = jwtProvider.generateMainToken(
                "123123",
                MemberRole.ADMIN,
                Instant.now().plusSeconds(60)
        );
        mockMvc.perform(MockMvcRequestBuilders.get("/api/member/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + jwtToken)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Lista de membros"))
                .andExpect(jsonPath("$.data").isArray());
    }
}
