package com.fpd.jpaspring.service;

import com.fpd.jpaspring.controller.dto.MemberCreateDto;
import com.fpd.jpaspring.controller.dto.MemberResponseDto;
import com.fpd.jpaspring.controller.dto.MemberUpdateDto;
import com.fpd.jpaspring.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {

    /**
     * member 목록 조회
     *
     */
    List<Member> getMembers();

    /**
     * member 조회
     *
     * @param id: member_id
     * @return
     */
    Member getMember(Long id);

    /**
     * member 생성
     *
     * @param memberCreateDto: member 생성용 Dto
     * @return
     */
    Long createMember(MemberCreateDto memberCreateDto);

    /**
     * Team & Member 조회
     *
     * @param pageable
     * @return
     */
    Page<MemberResponseDto> getTeamMember(Pageable pageable);

    Long updateMember(MemberUpdateDto memberUpdateDto);
}
