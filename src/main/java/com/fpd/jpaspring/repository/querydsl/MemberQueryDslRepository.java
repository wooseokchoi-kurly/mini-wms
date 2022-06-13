package com.fpd.jpaspring.repository.querydsl;

import com.fpd.jpaspring.controller.dto.MemberResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberQueryDslRepository {
    Page<MemberResponseDto> getTeamMember(Pageable pageable);
}
