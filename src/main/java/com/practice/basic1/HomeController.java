package com.practice.basic1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.basic1.boundedContext.Member.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

@Controller
public class HomeController {

    int cnt = 0;
    int lastIdx = 0;
    List<Map<String, Object>> peopleList = new ArrayList<>();

    @GetMapping("/home/main")
    @ResponseBody
    public String showMain() {
        return "안녕하세요.";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3() {
        return "즐거웠습니다.";
    }

    @GetMapping("/home/count")
    @ResponseBody
    public String showCount() {
        return String.valueOf(cnt++);
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/home/people")
    @ResponseBody
    public String showPeople() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(peopleList);
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPeople(String name, int age) {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("id", ++lastIdx);
        map.put("name", name);
        map.put("age", age);

        peopleList.add(map);

        return lastIdx + "번 사람이 추가되었습니다.";
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePeople(int id) {

        for (int i = 0; i < peopleList.size(); i++) {
            if (peopleList.get(i).get("id").equals(id)) {
                peopleList.remove(i);

                return id + "번 사람이 삭제되었습니다.";
            }
        }

        return id + "번 사람이 존재하지 않습니다.";
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPeople(int id, String name, int age) {

        for (int i = 0; i < peopleList.size(); i++) {

            if (peopleList.get(i).get("id").equals(id)) {
                peopleList.get(i).replace("name", name);
                peopleList.get(i).replace("age", age);

                return id + "번 사람이 수정되었습니다.";
            }
        }

        return id + "번 사람이 존재하지 않습니다.";
    }

    @GetMapping("/home/cookie/increase")
    @ResponseBody
    public int increaseCookie(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int cntInCookie = 0;

        if (req.getCookies() != null) {
            cntInCookie = Arrays.stream(req.getCookies()).filter(e -> e.getName().equals("count"))
                    .map(Cookie::getValue).mapToInt(Integer::parseInt).findFirst().orElse(0);
        }

        res.addCookie(new Cookie("count", ++cntInCookie + ""));

        return cntInCookie;
    }
}