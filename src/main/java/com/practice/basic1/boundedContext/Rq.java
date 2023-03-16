package com.practice.basic1.boundedContext;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

    public void setSesstion(String name, long value) {
        HttpSession session = request.getSession();
        session.setAttribute(name, value);
    }

    public long getSesstionAsLong (String name, long defaultValue) {
        String value = getSesstionAsStr(name, null);
        if (value == null) return defaultValue;

        try {
            return Long.parseLong(value);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // 세션 값 받기
    public String getSesstionAsStr (String name, String defaultValue) {
        try {
            String value = (String) request.getSession().getAttribute(name);
            if (value == null) return defaultValue;
            return value;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // 세션 삭제
    public boolean removeSession(String name) {
        HttpSession session = request.getSession();

        if (session.getAttribute(name) == null) return false;
        session.removeAttribute(name);
        return true;
    }

    public String getSessionDebugContents() {
        return "";
    }
}
