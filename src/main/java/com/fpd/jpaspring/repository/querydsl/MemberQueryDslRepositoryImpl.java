package com.fpd.jpaspring.repository.querydsl;

import com.fpd.jpaspring.common.QueryDslUtils;
import com.fpd.jpaspring.controller.dto.MemberResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.fpd.jpaspring.domain.QMember.member;
import static com.fpd.jpaspring.domain.QTeam.team;

@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MemberResponseDto> getTeamMember(Pageable pageable) {
        List<MemberResponseDto> dtoList = queryFactory.select(
                        Projections.constructor(
                                MemberResponseDto.class,
                                member.id.as("memberId"),
                                team.id.as("teamId"),
                                member.name,
                                team.teamName,
                                member.gender,
                                member.age
                        )
                ).from(team)
                .leftJoin(member).on(member.teamId.eq(team.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(dtoList, pageable, dtoList.size());
    }
}
