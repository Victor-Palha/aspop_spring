package com.victorpalha.aspop_spring.domain.member.repository;

import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<MemberEntity, String> {
    Optional<MemberEntity> findByEmailOrCpfOrRg(String email, String cpf, String rg);
}
