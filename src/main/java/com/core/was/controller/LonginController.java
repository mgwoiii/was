package com.core.was.controller;


import com.core.was.dto.Member;
import com.core.was.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/login")
@RestController
public class LonginController {

    @Autowired
    MemberService memberService;

    @PostMapping("/join")
    public void login(@RequestBody Member member){

        memberService.createUser(member);

    }
    @GetMapping("/test")
    public String test(){
    log.info("test message ::: {}", "TestMessage");

    return "res Return";
    }

}
