package com.board.server.member.mapper;

import com.board.server.member.dao.MemberDao;
import com.board.server.member.dto.request.DeleteMemberRequestDto;
import com.board.server.member.dto.request.MemberRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    // 모든 멤버 조회
    List<MemberDao> findAll();

    // 단일 멤버 조회
    MemberDao findById(Long sequence);

    // 단일 맴버 삽입
    int save(MemberRequestDto request);

    // 단일 맴버 수정
    int updateById(MemberRequestDto request);

    // 단일 맴버 삭제
    int deleteBySequences(List<MemberRequestDto> requestDto);
}
