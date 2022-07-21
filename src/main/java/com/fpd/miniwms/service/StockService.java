package com.fpd.miniwms.service;

import com.fpd.miniwms.controller.dto.request.StockReqDto;
import com.fpd.miniwms.controller.dto.response.StockResDto;

public interface StockService {

    /**
     * 재고 조회
     *
     * @param stockId: 재고 아이디
     * @return 조회한 재고
     */
    StockResDto getStock(Long stockId);

    /**
     * 아이템 아이디로 재고 조회
     * 조회가 되지 않으면 null 리턴..
     *
     * @param itemId: 아이템 아이디
     * @return 조회한 재고
     */
    StockResDto getStockByItemId(Long itemId);

    /**
     * 재고 증가
     *
     * @param stockReqDto: 재고 dto
     * @return 증가시킨 재고 아이디
     */
    Long inCreaseStock(StockReqDto stockReqDto);

    /**
     * 재고 감소
     *
     * @param stockReqDto: 재고 dto
     * @return 감소시킨 재고 아이디
     */
    Long deCreaseStock(StockReqDto stockReqDto);


}
