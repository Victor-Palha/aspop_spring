package com.victorpalha.aspop_spring.domain.member.useCases;


import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.exceptions.MemberNotFoundError;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetMemberByIdUseCaseTest {
    @InjectMocks
    private GetMemberByIdUseCase getMemberByIdUseCase;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void should_be_able_to_get_member_by_id() {
        MemberEntity member = new MemberEntity();
        member.setMemberId("1");
        member.setMemberName("John Doe");

        when(memberRepository.findById("1")).thenReturn(Optional.of(member));

        MemberEntity memberSearched = getMemberByIdUseCase.execute("1");

        assert memberSearched != null;
        assert memberSearched.getMemberId().equals("1");
        assert memberSearched.getMemberName().equals("John Doe");
    }

    @Test
    public void should_not_be_able_to_get_member_by_id_if_does_not_exist() {
        when(memberRepository.findById("1")).thenReturn(Optional.empty());
        try{
            getMemberByIdUseCase.execute("1");
        } catch (Exception e) {
            assert e instanceof MemberNotFoundError;
        }

    }
}
