package com.practice.basic1.boundedContext.article.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.basic1.boundedContext.article.entitiy.Article;
import com.practice.basic1.boundedContext.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleRepository articleRepository;
    @GetMapping ("/write")
    @ResponseBody
    public String write(String title, String body) throws JsonProcessingException {
        Map<String, Object> map = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        Article article = Article
                .builder()
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);

        map.put("resultCode", "S-1");
        map.put("msg","1번 글이 생성되었습니다.");
        map.put("data", article);

        return objectMapper.writeValueAsString(map);
    }
}
