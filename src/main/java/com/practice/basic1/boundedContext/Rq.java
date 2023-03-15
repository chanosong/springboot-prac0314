package com.practice.basic1.boundedContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

@AllArgsConstructor
@Data
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
