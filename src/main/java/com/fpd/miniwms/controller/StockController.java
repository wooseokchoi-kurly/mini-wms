package com.fpd.miniwms.controller;

import com.fpd.miniwms.controller.dto.response.StockResDto;
import com.fpd.miniwms.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "4. 재고 API")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping("/v1/stocks/{stockId}")
    @Operation(summary = "재고 아이디로 재고 조회 API", description = "재고 아이디로 조회")
    public ResponseEntity<?> getStockByStockId(@Parameter(description = "재고 아이디", required = true, example = "1")
                                               @PathVariable Long stockId) {
        StockResDto stock = stockService.getStock(stockId);
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/v1/stocks/items/{itemId}")
    @Operation(summary = "아이템 아이디로 재고 조회 API", description = "아이템 아이디로 조회")
    public ResponseEntity<?> getStockByItemId(@Parameter(description = "아이템 아이디", required = true, example = "1")
                                              @PathVariable Long itemId) {
        StockResDto stock = stockService.getStockByItemId(itemId);
        return ResponseEntity.ok(stock);
    }
}
