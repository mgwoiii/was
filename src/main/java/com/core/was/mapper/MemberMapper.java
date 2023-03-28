package com.core.was.mapper;
import com.core.was.dto.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public void createUser(Member member);

    Member readMember(Member member);
}
