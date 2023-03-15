package com.practice.basic1.boundedContext.Member;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Map<String, Object> tryLogin(String username, String password) {
        Map<String, Object> map = new LinkedHashMap<>();

        switch(memberRepository.validate(username, password)) {
            case 0:
                map.put("resultCode", "S-1");
                map.put("msg", username + "님 환영합니다.");
                break;
            case 1:
                map.put("resultCode", "F-2");
                map.put("msg", username + "(은)는 존재하지 않는 회원입니다.");
                break;
            case 2:
                map.put("resultCode", "F-1");
                map.put("msg","비밀번호가 일치하지 않습니다.");

        }

        return map;
    }

    public Map<String, Object> checkUsername(String username, String password) {
        Map<String, Object> map = new LinkedHashMap<>();

        if (memberRepository.validate(username, password) == 0){
            map.put("resultCode", "S-1");
            map.put("msg", "당신의 username(은)는 " + username + "입니다.");
        }

        return map;
    }
}
