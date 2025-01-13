package com.board.server.member.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDao {
    private Long sequence;
    private String id;
    private String name;
    private int age;
    private String createDate;
}
