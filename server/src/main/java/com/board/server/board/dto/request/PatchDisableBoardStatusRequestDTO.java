package com.board.server.board.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class PatchDisableBoardStatusRequestDTO {
    private List<Long> idList;
}
