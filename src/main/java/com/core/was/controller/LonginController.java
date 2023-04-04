package com.core.was.controller;


import com.core.was.dto.Member;
import com.core.was.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Slf4j
@RequestMapping("/api/login")
@RestController
public class LonginController {

    @Autowired
    MemberService memberService;

    @PostMapping("/join")
    public void login(@RequestBody Member member, HttpServletResponse response) throws Exception{

        Integer number = memberService.checkLoginId(member);
        log.info("number ::: {}", number);
        if(number == 0){

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            PrintWriter out = response.getWriter();
            //create Json Object
            JSONObject json = new JSONObject();

            // put some value pairs into the JSON object .
            try {
                json.put("status", 8002);
                json.put("message", "이미 등록된 아이디가 존재합니다.");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            out.print(json.toString());

        }else {
            memberService.createUser(member);
        }

    }
    @GetMapping("/test")
    public Object test(HttpServletRequest request , HttpServletResponse response)throws Exception {
        log.info("test message ::: {}", "TestMessage");


        HttpSession session = request.getSession();
        Object securityContextObject = session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        if (securityContextObject != null) {
            SecurityContext securityContext = (SecurityContext) securityContextObject;
            Authentication authentication = securityContext.getAuthentication();

            // 비로그인 접근
            if (authentication instanceof AnonymousAuthenticationToken) {
                return securityContextObject;
            }
        }
        return securityContextObject;
    }
    @GetMapping("/check")
    public String check(){
    log.info("test check ::: {}", "Testcheck");

    return "res check";
    }

}
