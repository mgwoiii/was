package com.core.was.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    private final String DEFAULT_FAILURE_URL = "/";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        //create Json Object
        JSONObject json = new JSONObject();

        // put some value pairs into the JSON object .
        try {
            json.put("status", 4444);
            json.put("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        // finally output the json string
        out.print(json.toString());
    }

}
