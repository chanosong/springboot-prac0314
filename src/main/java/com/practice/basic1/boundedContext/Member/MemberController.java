package com.practice.basic1.boundedContext.Member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/login")
    @ResponseBody
    public String login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String, Object> map = memberService.tryLogin(username, password);

        if (map.get("resultCode").toString().startsWith("S")) {
            res.addCookie(new Cookie("username", username));
            res.addCookie(new Cookie("password", password));
        }

        return objectMapper.writeValueAsString(map);
    }

    @GetMapping("/member/me")
    @ResponseBody
    public String showMe(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        if (req.getCookies() == null) {
            map.put("resultCode" , "F-1");
            map.put("msg", "로그인 후 이용해주세요.");
        }
        else {
            String username = Arrays.stream(req.getCookies()).filter(e -> e.getName().equals("username"))
                    .map(Cookie::getValue).findFirst().orElse("");
            String password = Arrays.stream(req.getCookies()).filter(e -> e.getName().equals("password"))
                    .map(Cookie::getValue).findFirst().orElse("");

            map = memberService.checkUsername(username, password);
        }

        return objectMapper.writeValueAsString(map);
    }
}