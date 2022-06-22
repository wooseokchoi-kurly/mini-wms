package com.fpd.jpaspring.service;

import com.fpd.jpaspring.controller.dto.MemberCreateDto;
import com.fpd.jpaspring.controller.dto.MemberResponseDto;
import com.fpd.jpaspring.controller.dto.MemberUpdateDto;
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

    @Override
    @Transactional
    public Long updateMember(MemberUpdateDto memberUpdateDto) {
        // 영속화
        Member member = memberRepository.findById(memberUpdateDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 더티 체킹
        // 변경감지 -> 바뀐 값이 있으면 update query!!
        member.setGender(memberUpdateDto.getGender());
        member.setName(memberUpdateDto.getName());

        // save는 따로 안해줘도 됩니다!
//        memberRepository.save(member);

        return member.getId();
    }
}
