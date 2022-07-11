package com.fpd.miniwms.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "입고 API")
@RequiredArgsConstructor
public class InboundController {

//    private final MemberService memberService;
//
//    @GetMapping("/v1/members")
//    @Operation(summary = "전체 회원 조회 API", description = "전체 회원 조회")
//    public ResponseEntity<?> getMembers() {
//        List<Member> members = memberService.getMembers();
//        return ResponseEntity.ok(members);
//    }
//
//    @GetMapping("/v1/members/teams")
//    @Operation(summary = "전체 팀 회원 조회 API", description = "전체 팀 회원 조회")
//    public ResponseEntity<?> getTeamMember(@ParameterObject
//                                           @PageableDefault(page = 0, size = 5, sort = "memberId", direction = Sort.Direction.DESC)
//                                           Pageable pageable) {
//        Page<MemberResponseDto> members = memberService.getTeamMember(pageable);
//        return ResponseEntity.ok(members);
//    }
//
//    @GetMapping("/v1/members/{memberId}")
//    @Operation(summary = "회원 조회 API", description = "회원 조회")
//    public ResponseEntity<?> getMember(@Parameter(description = "회원 아이디", required = true, example = "1")
//                                       @PathVariable Long memberId) {
//        Member member = memberService.getMember(memberId);
//        return ResponseEntity.ok(member);
//    }
//
//    @PostMapping("/v1/members")
//    @Operation(summary = "회원 등록 API", description = "회원 등록")
//    public ResponseEntity<?> createMember(@RequestBody MemberCreateDto memberCreateDto) {
//        Long memberId = memberService.createMember(memberCreateDto);
//        return ResponseEntity.ok(memberId);
//    }
//
//    @PutMapping("/v1/members")
//    @Operation(summary = "회원 수정 API", description = "회원 수정")
//    public ResponseEntity<?> updateMember(@RequestBody MemberUpdateDto memberUpdateDto) {
//        Long memberId = memberService.updateMember(memberUpdateDto);
//        return ResponseEntity.ok(memberId);
//    }
}
