package com.fpd.miniwms.controller;

import com.fpd.miniwms.controller.dto.request.OutboundCreateReqDto;
import com.fpd.miniwms.controller.dto.request.OutboundUpdateReqDto;
import com.fpd.miniwms.controller.dto.response.OutboundListResDto;
import com.fpd.miniwms.controller.dto.response.OutboundResDto;
import com.fpd.miniwms.service.OutboundService;
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
@Tag(name = "3. 출고 API")
@RequiredArgsConstructor
public class OutboundController {
    private final OutboundService outboundservice;

    @GetMapping("/v1/outbound")
    @Operation(summary = "출고 목록 조회 API", description = "출고 목록 조회")
    public ResponseEntity<?> getOutboundList(@ParameterObject
                                             @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC)
                                             Pageable pageable) {
        Page<OutboundListResDto> outboundList = outboundservice.getOutboundList(pageable);
        return ResponseEntity.ok(outboundList);
    }

    @GetMapping("/v1/outbound/{outboundHeaderId}")
    @Operation(summary = "출고 조회 API", description = "출고 조회")
    public ResponseEntity<?> getOutbound(@Parameter(description = "출고 헤더 아이디", required = true, example = "1")
                                         @PathVariable Long outboundHeaderId) {
        OutboundResDto outbound = outboundservice.getOutbound(outboundHeaderId);
        return ResponseEntity.ok(outbound);
    }

    @PostMapping("/v1/outbound")
    @Operation(summary = "출고 생성 API", description = "출고 생성")
    public ResponseEntity<?> createOutbound(@RequestBody OutboundCreateReqDto outboundCreateReqDto) {
        Long outboundHeaderId = outboundservice.createOutbound(outboundCreateReqDto);
        return ResponseEntity.ok(outboundHeaderId);
    }

    @PutMapping("/v1/outbound")
    @Operation(summary = "출고 수정 API", description = "출고 수정")
    public ResponseEntity<?> updateOutbound(@RequestBody OutboundUpdateReqDto OutboundUpdateReqDto) {
        Long outboundHeaderId = outboundservice.updateOutbound(OutboundUpdateReqDto);
        return ResponseEntity.ok(outboundHeaderId);
    }

    @PutMapping("/v1/outbound/{outboundHeaderId}/complete")
    @Operation(summary = "출고 완료 API", description = "출고 완료")
    public ResponseEntity<?> completeOutbound(@Parameter(description = "출고 헤더 아이디", required = true, example = "1")
                                              @PathVariable Long outboundHeaderId) {
        Long completeOutboundHeaderId = outboundservice.outboundComplete(outboundHeaderId);
        return ResponseEntity.ok(completeOutboundHeaderId);
    }

    @DeleteMapping("/v1/outbound/{outboundHeaderId}")
    @Operation(summary = "출고 삭제 API", description = "출고 삭제")
    public ResponseEntity<?> deleteOutbound(@Parameter(description = "출고 헤더 아이디", required = true, example = "1")
                                            @PathVariable Long outboundHeaderId) {
        outboundservice.deleteOutbound(outboundHeaderId);
        return ResponseEntity.ok(null);
    }

}
