package com.board.server.member.controller;

import com.board.server.common.api.ApiResponse;
import com.board.server.member.dto.MemberDto;
import com.board.server.member.dto.request.DeleteMemberRequestDto;
import com.board.server.member.dto.request.MemberRequestDto;
import com.board.server.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberDto>>> findAll() {
        return memberService.findAll();
    }

    @GetMapping("{sequence}")
    public ResponseEntity<ApiResponse<MemberDto>> findById(@PathVariable("sequence") Long sequence) {
        return memberService.findById(sequence);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> save(@Valid @RequestBody MemberRequestDto requestDto) {
        return memberService.save(requestDto);
    }

    @PatchMapping("{sequence}")
    public ResponseEntity<ApiResponse<Integer>> updateById(@PathVariable("sequence") Long sequence, @Valid @RequestBody MemberRequestDto request) {
        return memberService.updateById(sequence, request);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Integer>> deleteBySequences(@RequestBody List<MemberRequestDto> requestDto) {
        return memberService.deleteBySequences(requestDto);
    }

}
