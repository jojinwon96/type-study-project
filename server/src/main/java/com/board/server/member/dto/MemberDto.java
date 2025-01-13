package com.board.server.member.dto;

import com.board.server.member.dao.MemberDao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private Long sequence;
    private String id;
    private String name;
    private int age;
    private String createDate;

    public static MemberDto toMember(MemberDao memberDao) {
        return MemberDto.builder()
                .sequence(memberDao.getSequence())
                .id(memberDao.getId())
                .name(memberDao.getName())
                .age(memberDao.getAge())
                .createDate(memberDao.getCreateDate())
                .build();
    }


}
