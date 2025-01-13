package com.board.server.member.service;

import com.board.server.common.api.ApiResponse;
import com.board.server.member.dto.MemberDto;
import com.board.server.member.dto.request.DeleteMemberRequestDto;
import com.board.server.member.dto.request.MemberRequestDto;
import com.board.server.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemberService{
    private final MemberMapper memberMapper;

    /**
     * 사원 전체 조회
     */
    public ResponseEntity<ApiResponse<List<MemberDto>>> findAll() {
        List<MemberDto> members = memberMapper.findAll().stream()
                .map(MemberDto::toMember)
                .collect(Collectors.toList());

        return ApiResponse.success(members);
    }

    /**
     * 단일 사원 조회
     */
    public ResponseEntity<ApiResponse<MemberDto>> findById(Long sequence){
        MemberDto member = MemberDto.toMember(memberMapper.findById(sequence));
        if (member == null) {
            return ApiResponse.memberNotFound();
        } else {
            return ApiResponse.success(member);
        }
    }

    /**
     * 단일 사원 삽입
     */
    public ResponseEntity<ApiResponse<Integer>> save(MemberRequestDto request){
        int resultRow = memberMapper.save(request);
        return ApiResponse.success(resultRow);
    }

    /**
     * 단일 사원 수정
     */
    public ResponseEntity<ApiResponse<Integer>> updateById(Long sequence, MemberRequestDto request) {
        request.setSequence(sequence);
        int resultRow = memberMapper.updateById(request);
        return ApiResponse.success(resultRow);
    }

    /**
     * 단일 맴버 삭제
     */
    public ResponseEntity<ApiResponse<Integer>> deleteBySequences(List<MemberRequestDto> requestDto) {
        int resultRow = memberMapper.deleteBySequences(requestDto);
        return ApiResponse.success(resultRow);
    }

}
