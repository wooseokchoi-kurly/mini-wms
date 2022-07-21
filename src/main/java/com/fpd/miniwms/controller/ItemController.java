package com.fpd.miniwms.controller;

import com.fpd.miniwms.controller.dto.request.ItemCreateReqDto;
import com.fpd.miniwms.controller.dto.request.ItemUpdateReqDto;
import com.fpd.miniwms.controller.dto.response.ItemResDto;
import com.fpd.miniwms.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "아이템 API")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/v1/items/{itemId}")
    @Operation(summary = "아이템 아이디로 조회 API", description = "아이템 아이디로 조회")
    public ResponseEntity<?> getItemByItemId(@Parameter(description = "아이템 아이디", required = true, example = "1")
                                             @PathVariable Long itemId) {
        ItemResDto item = itemService.getItemByItemId(itemId);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/v1/items/name")
    @Operation(summary = "아이템 이름으로 조회 API", description = "아이템 이름으로 조회")
    public ResponseEntity<?> getItemListLikeItemName(@Parameter(description = "아이템 이름", required = true, example = "키보드") String itemName) {
        List<ItemResDto> itemResDtoList = itemService.getItemListLikeItemName(itemName);
        return ResponseEntity.ok(itemResDtoList);
    }

    @PostMapping("/v1/items")
    @Operation(summary = "아이템 생성 API", description = "아이템 생성")
    public ResponseEntity<?> createItem(@RequestBody ItemCreateReqDto itemCreateReqDto) {
        Long itemId = itemService.createItem(itemCreateReqDto);
        return ResponseEntity.ok(itemId);
    }

    @PutMapping("/v1/items")
    @Operation(summary = "아이템 수정 API", description = "아이템 수정")
    public ResponseEntity<?> updateItem(@RequestBody ItemUpdateReqDto itemUpdateReqDto) {
        Long itemId = itemService.updateItem(itemUpdateReqDto);
        return ResponseEntity.ok(itemId);
    }
}
