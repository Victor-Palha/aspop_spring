package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.constants.MemberRole;
import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.InvalidCredentialsError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticateMemberUseCaseTest {
    @InjectMocks
    private AuthenticateMemberUseCase authenticateMemberUseCase;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void should_authenticate_member() {
        String memberId = "123123";
        String password = new BCryptPasswordEncoder().encode("password123");
        MemberEntity memberToBeAuthenticated = MemberEntity
                .builder()
                .memberId(memberId)
                .password(password)
                .cpf("12345678900")
                .memberName("Test")
                .role(MemberRole.ADMIN)
                .build();

        when(memberRepository.findByCpf("12345678900")).thenReturn(Optional.of(memberToBeAuthenticated));

        MemberEntity result = authenticateMemberUseCase.execute("12345678900", "password123");
        assert memberId.equals(result.getMemberId());
    }

    @Test
    public void should_not_authenticate_member_with_wrong_password() {
        String memberId = "123123";
        String password = new BCryptPasswordEncoder().encode("password123");
        MemberEntity memberToBeAuthenticated = MemberEntity
                .builder()
                .memberId(memberId)
                .password(password)
                .cpf("12345678900")
                .memberName("Test")
                .role(MemberRole.ADMIN)
                .build();

        when(memberRepository.findByCpf("12345678900")).thenReturn(Optional.of(memberToBeAuthenticated));


        try{
            authenticateMemberUseCase.execute("12345678900", "password1234");
        } catch (Exception e) {
            assert e instanceof InvalidCredentialsError;
        }

    }
    @Test
    public void should_not_authenticate_member_with_wrong_cpf() {
        when(memberRepository.findByCpf("12345678900")).thenReturn(Optional.empty());

        try{
            authenticateMemberUseCase.execute("12345678900", "password1234");
        } catch (Exception e) {
            assert e instanceof InvalidCredentialsError;
        }

    }
}
