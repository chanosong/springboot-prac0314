package com.practice.basic1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    int cnt = 0;
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

        map.put("id", peopleList.size() + 1);
        map.put("name", name);
        map.put("age", age);

        peopleList.add(map);

        return peopleList.size() + "번 사람이 추가되었습니다.";
    }
}