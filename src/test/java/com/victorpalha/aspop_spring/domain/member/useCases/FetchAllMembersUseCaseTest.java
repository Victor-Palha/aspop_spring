package com.victorpalha.aspop_spring.domain.member.useCases;

import com.victorpalha.aspop_spring.domain.member.entities.MemberEntity;
import com.victorpalha.aspop_spring.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchAllMembersUseCaseTest {
    @InjectMocks
    private FetchAllMembersUseCase fetchAllMembersUseCase;

    @Mock
    private MemberRepository memberRepository;

    @Test
    void should_fetch_all_members_successfully() {
        // Mock data
        MemberEntity member1 = new MemberEntity();
        member1.setMemberId("1");
        member1.setMemberName("John Doe");

        MemberEntity member2 = new MemberEntity();
        member2.setMemberId("2");
        member2.setMemberName("Jane Smith");

        List<MemberEntity> mockMembers = Arrays.asList(member1, member2);

        // Mock behavior
        when(memberRepository.findAll()).thenReturn(mockMembers);

        // Execute use case
        List<MemberEntity> fetchedMembers = fetchAllMembersUseCase.execute();
        // Assertions
        assert fetchedMembers != null;
        assert fetchedMembers.size() == 2;
        assert "John Doe".equals(fetchedMembers.get(0).getMemberName());
        assert "Jane Smith".equals(fetchedMembers.get(1).getMemberName());
    }

    @Test
    void should_return_empty_list_when_no_members_exist() {
        // Mock behavior
        when(memberRepository.findAll()).thenReturn(List.of());

        // Execute use case
        List<MemberEntity> fetchedMembers = fetchAllMembersUseCase.execute();

        // Assertions
        assert fetchedMembers != null;
        assert fetchedMembers.isEmpty();
    }
}
