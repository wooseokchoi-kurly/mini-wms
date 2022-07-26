package com.fpd.miniwms.repository.querydsl;

import com.fpd.miniwms.common.status.OutboundStatus;
import com.fpd.miniwms.controller.dto.response.OutboundListResDto;
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

import static com.fpd.miniwms.domain.QItem.item;
import static com.fpd.miniwms.domain.QOutboundDetail.outboundDetail;
import static com.fpd.miniwms.domain.QOutboundHeader.outboundHeader;

@Repository
@RequiredArgsConstructor
public class OutboundQuerydslRepositoryImpl implements OutboundQuerydslRepository {

    private final JPAQueryFactory query;

    @Override
    @Transactional(readOnly = true)
    public Page<OutboundListResDto> getOutboundList(Pageable pageable) {
        List<OutboundListResDto> outboundListResDtoList = query.select(Projections.constructor(OutboundListResDto.class,
                        outboundHeader.outboundPic.as("outboundPic"),
                        new CaseBuilder()
                                .when(outboundHeader.isOutboundComplete.eq(true))
                                .then(OutboundStatus.COMPLETE.getName())
                                .otherwise(OutboundStatus.READY.getName()).as("outboundStatus"),
                        item.itemName.max().as("representItemName"),
                        outboundHeader.createdDate.as("createdDate"),
                        outboundHeader.updatedDate.as("updatedDate")
                ))
                .from(outboundHeader)
                .leftJoin(outboundDetail).on(outboundDetail.outboundHeader.id.eq(outboundHeader.id))
                .leftJoin(item).on(item.id.eq(outboundDetail.item.id))
                .where(outboundHeader.isDelete.eq(false))
                .groupBy(outboundHeader.outboundPic, outboundHeader.createdDate, outboundHeader.updatedDate)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(outboundListResDtoList, pageable, outboundListResDtoList.size());
    }
}
