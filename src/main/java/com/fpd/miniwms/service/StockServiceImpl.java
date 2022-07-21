package com.fpd.miniwms.service;

import com.fpd.miniwms.controller.dto.request.StockReqDto;
import com.fpd.miniwms.controller.dto.response.ItemResDto;
import com.fpd.miniwms.controller.dto.response.StockResDto;
import com.fpd.miniwms.domain.Item;
import com.fpd.miniwms.domain.Stock;
import com.fpd.miniwms.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    private final ItemService itemService;

    @Override
    @Transactional(readOnly = true)
    public StockResDto getStock(Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 재고입니다."));

        Item item = stock.getItem();

        return StockResDto.builder()
                .stockId(stockId)
                .itemName(item.getItemName())
                .itemId(item.getId())
                .stockQty(stock.getStockQty())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public StockResDto getStockByItemId(Long itemId) {
        Stock stock = stockRepository.findByItemId(itemId).orElse(null);

        // TODO: 2022/07/22 더 좋은 방법은 없을까?
        if (stock == null) return null;

        Item item = stock.getItem();

        return StockResDto.builder()
                .stockId(stock.getId())
                .itemName(item.getItemName())
                .itemId(item.getId())
                .stockQty(stock.getStockQty())
                .build();
    }

    @Override
    @Transactional
    public Long inCreaseStock(StockReqDto stockReqDto) {
        Integer updateStockQty = stockReqDto.getUpdateStockQty();

        if (updateStockQty == 0 || updateStockQty < -1) {
            throw new IllegalArgumentException("업데이트 수량은 0보다 커야합니다.");
        }

        // 증가시킬 재고가 없다면 재고 생성
        Long stockId = stockReqDto.getStockId();
        Stock stock = stockRepository.findById(stockId)
                .orElseGet(() -> createStock(stockReqDto));

        // 재고 증가
        int updatedStockQty = stockId == 0 ? updateStockQty : stock.getStockQty() + updateStockQty;
        stock.setStockQty(updatedStockQty);

        return stock.getId();
    }

    @Override
    @Transactional
    public Long deCreaseStock(StockReqDto stockReqDto) {
        Integer updateStockQty = stockReqDto.getUpdateStockQty();

        if (updateStockQty == 0 || updateStockQty < -1) {
            throw new IllegalArgumentException("업데이트 수량은 0보다 커야합니다.");
        }

        Stock stock = stockRepository.findById(stockReqDto.getStockId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 재고입니다."));

        // 재고 감소
        int updatedStockQty = stock.getStockQty() - updateStockQty;

        if (updatedStockQty < 0) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }

        stock.setStockQty(updatedStockQty);

        return stock.getId();
    }

    protected Stock createStock(StockReqDto stockReqDto) {
        ItemResDto item = itemService.getItemByItemId(stockReqDto.getItemId());

        Stock stock = Stock.builder()
                .item(Item.of(item))
                .stockQty(stockReqDto.getUpdateStockQty())
                .build();

        return stockRepository.save(stock);
    }
}
