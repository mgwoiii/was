package com.core.was.controller;


import com.core.was.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/login")
@RestController
public class LonginController {

    @PostMapping("/join")
    public void login(@RequestBody Member member){

    }

}
