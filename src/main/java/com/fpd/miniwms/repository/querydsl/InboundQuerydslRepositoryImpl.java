package com.fpd.miniwms.repository.querydsl;

import com.fpd.miniwms.common.status.InboundStatus;
import com.fpd.miniwms.controller.dto.response.InboundListResDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.fpd.miniwms.domain.QInboundDetail.inboundDetail;
import static com.fpd.miniwms.domain.QInboundHeader.inboundHeader;
import static com.fpd.miniwms.domain.QItem.item;

@Repository
@RequiredArgsConstructor
public class InboundQuerydslRepositoryImpl implements InboundQuerydslRepository {

    private final JPAQueryFactory query;

    @Override
    @Transactional(readOnly = true)
    public Page<InboundListResDto> getInboundList(Pageable pageable) {
        List<InboundListResDto> inboundListResDtoList = query.select(Projections.constructor(InboundListResDto.class,
                        inboundHeader.inboundPic.as("inboundPic"),
                        new CaseBuilder()
                                .when(inboundHeader.isInboundComplete.eq(true))
                                .then(InboundStatus.COMPLETE.getName())
                                .otherwise(InboundStatus.READY.getName()).as("inboundStatus"),
                        item.itemName.max().as("representItemName"),
                        inboundHeader.createdDate.as("createdDate"),
                        inboundHeader.updatedDate.as("updatedDate")
                ))
                .from(inboundHeader)
                .leftJoin(inboundDetail).on(inboundDetail.inboundHeader.id.eq(inboundHeader.id))
                .leftJoin(item).on(item.id.eq(inboundDetail.item.id))
                .where(inboundHeader.isDelete.eq(false))
                .groupBy(inboundHeader.inboundPic, inboundHeader.createdDate, inboundHeader.updatedDate)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(inboundListResDtoList, pageable, inboundListResDtoList.size());
    }
}
