package com.fpd.miniwms.service;

import com.fpd.miniwms.controller.dto.request.ItemCreateReqDto;
import com.fpd.miniwms.controller.dto.request.ItemUpdateReqDto;
import com.fpd.miniwms.controller.dto.response.ItemResDto;

import java.util.List;

public interface ItemService {

    /**
     * 아이템 아이디로 조회
     *
     * @param itemId: 아이템 아이디
     * @return 아이템
     */
    ItemResDto getItemByItemId(Long itemId);

    /**
     * 아이템 이름으로 조회
     *
     * @param itemName: 아이템 이름
     * @return 아이템
     */
    List<ItemResDto> getItemListLikeItemName(String itemName);

    /**
     * 아이템 생성
     *
     * @param itemCreateReqDto: 아이템 생성 dto
     * @return 생성된 아이템 아이디
     */
    Long createItem(ItemCreateReqDto itemCreateReqDto);

    /**
     * 아이템 수정
     * IsItemUse == false 일 경우에만 수정 가능
     *
     * @param itemUpdateReqDto: 아이템 수정 dto
     * @return 수정된 아이템 아이디
     */
    Long updateItem(ItemUpdateReqDto itemUpdateReqDto);
}
