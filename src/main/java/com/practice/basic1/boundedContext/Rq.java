package com.practice.basic1.boundedContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

@AllArgsConstructor
@RequestScope
@Component
public class Rq {
    HttpServletRequest request;
    HttpServletResponse response;

    public void removeCookie(String name){

        if (request.getCookies() != null) {
            Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(name))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    });
        }
    }
}
