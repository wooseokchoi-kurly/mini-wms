package com.fpd.miniwms.service;

import com.fpd.miniwms.common.exception.ItemCannotUpdateException;
import com.fpd.miniwms.controller.dto.request.ItemCreateReqDto;
import com.fpd.miniwms.controller.dto.request.ItemUpdateReqDto;
import com.fpd.miniwms.controller.dto.response.ItemResDto;
import com.fpd.miniwms.domain.Item;
import com.fpd.miniwms.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public ItemResDto getItemByItemId(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다."));

        return ItemResDto.builder()
                .itemId(item.getId())
                .itemCode(item.getItemCode())
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .isItemUse(item.getIsItemUse())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemResDto> getItemListLikeItemName(String itemName) {
        List<Item> itemList = itemRepository.findByItemNameContainingIgnoreCase(itemName);

        return itemList.stream()
                .map(item -> ItemResDto.builder()
                        .itemId(item.getId())
                        .itemCode(item.getItemCode())
                        .itemName(item.getItemName())
                        .itemPrice(item.getItemPrice())
                        .isItemUse(item.getIsItemUse())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long createItem(ItemCreateReqDto itemCreateReqDto) {
        Item item = Item.builder()
                .itemCode(itemCreateReqDto.getItemCode())
                .itemName(itemCreateReqDto.getItemName())
                .itemPrice(itemCreateReqDto.getItemPrice())
                .build();

        return itemRepository.save(item).getId();
    }

    @Override
    @Transactional
    public Long updateItem(ItemUpdateReqDto itemUpdateReqDto) {
        Item item = itemRepository.findById(itemUpdateReqDto.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이템입니다."));

        if (item.getIsItemUse()) {
            throw new ItemCannotUpdateException("현재 사용중인 아이템은 수정이 불가능합니다.");
        }

        item.setItemCode(itemUpdateReqDto.getItemCode());
        item.setItemName(itemUpdateReqDto.getItemName());
        item.setItemPrice(itemUpdateReqDto.getItemPrice());

        return item.getId();
    }
}
