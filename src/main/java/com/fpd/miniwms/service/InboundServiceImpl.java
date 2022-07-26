package com.fpd.miniwms.service;

import com.fpd.miniwms.common.exception.InboundCannotUpdateException;
import com.fpd.miniwms.controller.dto.request.*;
import com.fpd.miniwms.controller.dto.response.*;
import com.fpd.miniwms.domain.InboundDetail;
import com.fpd.miniwms.domain.InboundHeader;
import com.fpd.miniwms.domain.Item;
import com.fpd.miniwms.repository.InboundDetailRepository;
import com.fpd.miniwms.repository.InboundHeaderRepository;
import com.fpd.miniwms.repository.querydsl.InboundQuerydslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InboundServiceImpl implements InboundService {

    private final InboundHeaderRepository inboundHeaderRepository;
    private final InboundDetailRepository inboundDetailRepository;
    private final InboundQuerydslRepository inboundQuerydslRepository;
    private final ItemService itemService;
    private final StockService stockService;

    @Override
    @Transactional(readOnly = true)
    public Page<InboundListResDto> getInboundList(Pageable pageable) {
        return inboundQuerydslRepository.getInboundList(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public InboundResDto getInbound(Long inboundHeaderId) {
        InboundHeader inboundHeader = inboundHeaderRepository.findById(inboundHeaderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 입고헤더입니다."));

        List<InboundDetail> inboundDetailList = inboundDetailRepository.findByInboundHeader(inboundHeader);

        List<InboundResDetailDto> inboundResDetailDtoList = inboundDetailList.stream()
                .map(detail -> InboundResDetailDto.builder()
                        .item(detail.getItem())
                        .inboundQty(detail.getInboundQty())
                        .build())
                .collect(Collectors.toList());

        return InboundResDto.builder()
                .inboundPic(inboundHeader.getInboundPic())
                .inboundResDetailDtoList(inboundResDetailDtoList)
                .build();
    }

    @Override
    @Transactional
    public Long createInbound(InboundCreateReqDto inboundCreateReqDto) {
        InboundHeader inboundHeader = InboundHeader.builder()
                .inboundPic(inboundCreateReqDto.getInboundPic())
                .build();

        //입고 헤더 생성
        InboundHeader savedInboundHeader = inboundHeaderRepository.save(inboundHeader);

        List<InboundCreateDetailReqDto> inboundCreateDetailReqDtoList = inboundCreateReqDto.getInboundCreateDetailReqDtoList();

        List<InboundDetail> inboundDetailList = inboundCreateDetailReqDtoList.stream().map(detail -> {
            ItemResDto itemResDto = itemService.getItemByItemId(detail.getItemId());

            return InboundDetail.builder()
                    .inboundHeader(savedInboundHeader)
                    .inboundQty(detail.getInboundQty())
                    .item(Item.of(itemResDto))
                    .build();
        }).collect(Collectors.toList());

        //입고 상세 생성
        inboundDetailRepository.saveAll(inboundDetailList);

        return savedInboundHeader.getId();
    }

    @Override
    @Transactional
    public Long updatedInbound(InboundUpdateReqDto inboundUpdateReqDto) {
        InboundHeader inboundHeader = inboundHeaderRepository.findById(inboundUpdateReqDto.getInboundHeaderId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 입고헤더입니다."));

        if (inboundHeader.getIsInboundComplete()) {
            throw new InboundCannotUpdateException();
        }

        List<InboundDetail> inboundDetailList = inboundDetailRepository.findByInboundHeader(inboundHeader);
        inboundDetailRepository.deleteAll(inboundDetailList);

        inboundHeader.setInboundPic(inboundUpdateReqDto.getInboundPic());

        List<InboundUpdateDetailReqDto> inboundUpdateDetailReqDtoList = inboundUpdateReqDto.getInboundUpdateDetailReqDtoList();

        List<InboundDetail> inboundDetailUpdatedList = inboundUpdateDetailReqDtoList.stream().map(detail -> {
            ItemResDto itemResDto = itemService.getItemByItemId(detail.getItemId());

            return InboundDetail.builder()
                    .inboundHeader(inboundHeader)
                    .inboundQty(detail.getInboundQty())
                    .item(Item.of(itemResDto))
                    .build();
        }).collect(Collectors.toList());

        inboundDetailRepository.saveAll(inboundDetailUpdatedList);

        return inboundHeader.getId();
    }

    @Override
    @Transactional
    public void deleteInbound(Long inboundHeaderId) {
        InboundHeader inboundHeader = inboundHeaderRepository.findById(inboundHeaderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 입고헤더입니다."));

        inboundHeaderRepository.deleteById(inboundHeader.getId());

        List<InboundDetail> inboundDetailList = inboundDetailRepository.findByInboundHeader(inboundHeader);
        inboundDetailRepository.deleteAll(inboundDetailList);
    }

    @Override
    @Transactional
    public Long inboundComplete(Long inboundHeaderId) {
        InboundHeader inboundHeader = inboundHeaderRepository.findById(inboundHeaderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 입고헤더입니다."));

        List<InboundDetail> inboundDetailList = inboundDetailRepository.findByInboundHeader(inboundHeader);

        inboundDetailList.forEach(detail -> {
            Item item = detail.getItem();
            StockResDto stockResDto = stockService.getStockByItemId(item.getId());

            StockReqDto stockReqDto = StockReqDto.builder()
                    .itemId(item.getId())
                    .updateStockQty(detail.getInboundQty())
                    .stockId(stockResDto == null ? 0 : stockResDto.getStockId())
                    .build();

            stockService.inCreaseStock(stockReqDto);
            item.setIsItemUse(true);
        });

        inboundHeader.setIsInboundComplete(true);

        return inboundHeader.getId();
    }
}
