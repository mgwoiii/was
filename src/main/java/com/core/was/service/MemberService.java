package com.core.was.service;

import com.core.was.dto.Member;
import com.core.was.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {


    @Autowired
    private MemberMapper memberMapper;


    @Transactional
    public void createUser(Member member) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        member.setLoginPassword(passwordEncoder.encode(member.getPassword()));

        try {
            memberMapper.createUser(member);

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Member selectMember = new Member();

        selectMember.setLoginId(loginId);

        Member member = memberMapper.readMember(selectMember);

        if (member == null){
            throw new UsernameNotFoundException("유저 정보가 없습니다.");
        }
        return member;
    }
}
