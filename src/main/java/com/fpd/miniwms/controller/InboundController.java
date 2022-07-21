package com.fpd.miniwms.controller;

import com.fpd.miniwms.controller.dto.request.InboundCreateReqDto;
import com.fpd.miniwms.controller.dto.request.InboundUpdateReqDto;
import com.fpd.miniwms.controller.dto.response.InboundListResDto;
import com.fpd.miniwms.controller.dto.response.InboundResDto;
import com.fpd.miniwms.service.InboundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "입고 API")
@RequiredArgsConstructor
public class InboundController {

    private final InboundService inboundService;

    @GetMapping("/v1/inbound")
    @Operation(summary = "입고 목록 조회 API", description = "입고 목록 조회")
    public ResponseEntity<?> getInboundList(@ParameterObject
                                            @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC)
                                            Pageable pageable) {
        Page<InboundListResDto> inboundList = inboundService.getInboundList(pageable);
        return ResponseEntity.ok(inboundList);
    }

    @GetMapping("/v1/inbound/{inboundHeaderId}")
    @Operation(summary = "입고 조회 API", description = "입고 조회")
    public ResponseEntity<?> getInbound(@Parameter(description = "입고 헤더 아이디", required = true, example = "1")
                                        @PathVariable Long inboundHeaderId) {
        InboundResDto inbound = inboundService.getInbound(inboundHeaderId);
        return ResponseEntity.ok(inbound);
    }

    @PostMapping("/v1/inbound")
    @Operation(summary = "입고 생성 API", description = "입고 생성")
    public ResponseEntity<?> createInbound(@RequestBody InboundCreateReqDto inboundCreateReqDto) {
        Long inboundHeaderId = inboundService.createInbound(inboundCreateReqDto);
        return ResponseEntity.ok(inboundHeaderId);
    }

    @PutMapping("/v1/inbound")
    @Operation(summary = "입고 수정 API", description = "입고 수정")
    public ResponseEntity<?> updateInbound(@RequestBody InboundUpdateReqDto inboundUpdateReqDto) {
        Long inboundHeaderId = inboundService.updatedInbound(inboundUpdateReqDto);
        return ResponseEntity.ok(inboundHeaderId);
    }

    @PutMapping("/v1/inbound/{inboundHeaderId}/complete")
    @Operation(summary = "입고 완료 API", description = "입고 완료")
    public ResponseEntity<?> completeInbound(@Parameter(description = "입고 헤더 아이디", required = true, example = "1")
                                             @PathVariable Long inboundHeaderId) {
        Long completeInboundHeaderId = inboundService.inboundComplete(inboundHeaderId);
        return ResponseEntity.ok(completeInboundHeaderId);
    }

    @DeleteMapping("/v1/inbound/{inboundHeaderId}")
    @Operation(summary = "입고 삭제 API", description = "입고 삭제")
    public ResponseEntity<?> deleteInbound(@Parameter(description = "입고 헤더 아이디", required = true, example = "1")
                                           @PathVariable Long inboundHeaderId) {
        inboundService.deleteInbound(inboundHeaderId);
        return ResponseEntity.ok(null);
    }

}
