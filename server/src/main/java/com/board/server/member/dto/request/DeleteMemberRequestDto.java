package com.board.server.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeleteMemberRequestDto {
    private List<Long> memberSequences;
}
