package com.fpd.jpaspring.service;

import com.fpd.jpaspring.controller.dto.MemberCreateDto;
import com.fpd.jpaspring.controller.dto.MemberResponseDto;
import com.fpd.jpaspring.domain.Member;
import com.fpd.jpaspring.repository.MemberRepository;
import com.fpd.jpaspring.repository.querydsl.MemberQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryDslRepository memberQueryDslRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public Long createMember(MemberCreateDto memberCreateDto) {
        Member member = memberCreateDto.toEntity();
        Member newMember = memberRepository.save(member);

        return newMember.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MemberResponseDto> getTeamMember(Pageable pageable) {
        return memberQueryDslRepository.getTeamMember(pageable);
    }
}
