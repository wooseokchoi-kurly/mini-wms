package com.fpd.miniwms.service;

import com.fpd.miniwms.common.exception.OutboundCannotUpdateException;
import com.fpd.miniwms.common.status.OutboundStatus;
import com.fpd.miniwms.controller.dto.request.*;
import com.fpd.miniwms.controller.dto.response.*;
import com.fpd.miniwms.domain.Item;
import com.fpd.miniwms.domain.OutboundDetail;
import com.fpd.miniwms.domain.OutboundHeader;
import com.fpd.miniwms.repository.OutboundDetailRepository;
import com.fpd.miniwms.repository.OutboundHeaderRepository;
import com.fpd.miniwms.repository.querydsl.OutboundQuerydslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OutboundServiceImpl implements OutboundService {

    private final OutboundHeaderRepository outboundHeaderRepository;
    private final OutboundDetailRepository outboundDetailRepository;
    private final OutboundQuerydslRepository outboundQuerydslRepository;
    private final ItemService itemService;
    private final StockService stockService;

    @Override
    @Transactional(readOnly = true)
    public Page<OutboundListResDto> getOutboundList(Pageable pageable) {
        return outboundQuerydslRepository.getOutboundList(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public OutboundResDto getOutbound(Long outboundHeaderId) {
        OutboundHeader outboundHeader = outboundHeaderRepository.findById(outboundHeaderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 출고헤더입니다."));

        List<OutboundDetail> outboundDetailList = outboundDetailRepository.findByOutboundHeader(outboundHeader);

        List<OutboundResDetailDto> outboundResDetailDtoList = outboundDetailList.stream()
                .map(detail -> OutboundResDetailDto.builder()
                        .item(detail.getItem())
                        .outboundQty(detail.getOutboundQty())
                        .build())
                .collect(Collectors.toList());

        return OutboundResDto.builder()
                .outboundPic(outboundHeader.getOutboundPic())
                .outboundStatus(outboundHeader.getIsOutboundComplete()
                        ? OutboundStatus.COMPLETE.getName()
                        : OutboundStatus.READY.getName())
                .outboundResDetailDtoList(outboundResDetailDtoList)
                .build();
    }

    @Override
    @Transactional
    public Long createOutbound(OutboundCreateReqDto outboundCreateReqDto) {
        OutboundHeader outboundHeader = OutboundHeader.builder()
                .outboundPic(outboundCreateReqDto.getOutboundPic())
                .build();

        OutboundHeader savedOutboundHeader = outboundHeaderRepository.save(outboundHeader);

        List<OutboundCreateDetailReqDto> outboundCreateDetailReqDtoList = outboundCreateReqDto.getOutboundCreateDetailReqDtoList();

        List<OutboundDetail> outboundDetailList = outboundCreateDetailReqDtoList.stream().map(detail -> {
            ItemResDto itemResDto = itemService.getItemByItemId(detail.getItemId());

            return OutboundDetail.builder()
                    .outboundHeader(savedOutboundHeader)
                    .outboundQty(detail.getOutboundQty())
                    .item(Item.of(itemResDto))
                    .build();
        }).collect(Collectors.toList());

        outboundDetailRepository.saveAll(outboundDetailList);

        return savedOutboundHeader.getId();
    }

    @Override
    @Transactional
    public Long updateOutbound(OutboundUpdateReqDto outboundUpdateReqDto) {
        OutboundHeader outboundHeader = outboundHeaderRepository.findById(outboundUpdateReqDto.getOutboundHeaderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 출고헤더입니다."));

        if (outboundHeader.getIsOutboundComplete()) {
            throw new OutboundCannotUpdateException();
        }

        List<OutboundDetail> outboundDetailList = outboundDetailRepository.findByOutboundHeader(outboundHeader);
        outboundDetailRepository.deleteAll(outboundDetailList);

        outboundHeader.setOutboundPic(outboundHeader.getOutboundPic());

        List<OutboundUpdateDetailReqDto> outboundUpdateDetailReqDtoList = outboundUpdateReqDto.getOutboundUpdateDetailReqDtoList();

        List<OutboundDetail> outboundDetailUpdatedList = outboundUpdateDetailReqDtoList.stream().map(detail -> {
            ItemResDto itemResDto = itemService.getItemByItemId(detail.getItemId());

            return OutboundDetail.builder()
                    .outboundHeader(outboundHeader)
                    .outboundQty(detail.getOutboundQty())
                    .item(Item.of(itemResDto))
                    .build();
        }).collect(Collectors.toList());

        outboundDetailRepository.saveAll(outboundDetailUpdatedList);

        return outboundHeader.getId();
    }

    @Override
    @Transactional
    public void deleteOutbound(Long outboundHeaderId) {
        OutboundHeader outboundHeader = outboundHeaderRepository.findById(outboundHeaderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 출고헤더입니다."));

        outboundHeaderRepository.deleteById(outboundHeader.getId());

        List<OutboundDetail> outboundDetailList = outboundDetailRepository.findByOutboundHeader(outboundHeader);
        outboundDetailRepository.deleteAll(outboundDetailList);
    }

    @Override
    @Transactional
    public Long outboundComplete(Long outboundHeaderId) {
        OutboundHeader outboundHeader = outboundHeaderRepository.findById(outboundHeaderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 출고헤더입니다."));

        List<OutboundDetail> outboundDetailList = outboundDetailRepository.findByOutboundHeader(outboundHeader);

        outboundDetailList.forEach(detail -> {
            Item item = detail.getItem();
            StockResDto stockResDto = stockService.getStockByItemId(item.getId());

            StockReqDto stockReqDto = StockReqDto.builder()
                    .itemId(item.getId())
                    .updateStockQty(detail.getOutboundQty())
                    .stockId(stockResDto == null ? 0 : stockResDto.getStockId())
                    .build();

            stockService.deCreaseStock(stockReqDto);
            item.setIsItemUse(true);

        });

        outboundHeader.setIsOutboundComplete(true);

        return outboundHeader.getId();
    }
}
