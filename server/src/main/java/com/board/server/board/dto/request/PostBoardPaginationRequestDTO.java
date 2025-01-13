package com.board.server.board.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostBoardPaginationRequestDTO {
    private int page;
    private int size;
}
