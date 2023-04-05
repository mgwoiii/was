package com.core.was.config.auth;

//import com.core.was.config.jwt.JwtTokenProvider;
import com.core.was.dto.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    // private JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("authentication :: {}", authentication);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        //create Json Object
        JSONObject json = new JSONObject();


        log.info("authentication.getDetails()2 :: {}", authentication.getPrincipal());

        Member member = (Member) authentication.getPrincipal();

        // put some value pairs into the JSON object .
        try {
            json.put("status", 200);
            json.put("message", "로그인.");
            json.put("getId", member.getId());
            json.put("getLoginId", member.getLoginId());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // finally output the json string
        out.print(json.toString());

//        out.print(jwtTokenProvider);
    }
}
