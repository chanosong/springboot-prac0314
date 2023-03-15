package com.practice.basic1.Repository;

import com.practice.basic1.Entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    static private List<Member> memberList = new ArrayList<>();

    public MemberRepository() {
        memberList.add(new Member("user1", "1234"));
        memberList.add(new Member("abc", "12345"));
        memberList.add(new Member("test", "12346"));
        memberList.add(new Member("love", "12347"));
        memberList.add(new Member("like", "12348"));
        memberList.add(new Member("giving", "12349"));
        memberList.add(new Member("thanks", "123410"));
        memberList.add(new Member("hello", "123411"));
        memberList.add(new Member("good", "123412"));
        memberList.add(new Member("peace", "123413"));
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void addMember(String username, String password) {
        memberList.add(new Member(username, password));
    }

    // 0: 성공 1: 아이디 존재 x 2: 비밀번호 틀림
    public int validate(String username, String password) {
        for (Member m : memberList) {
            if (m.getUsername().equals(username)) {
                if (m.getPassword().equals(password)) return 0;
                return 2;
            }
        }
        return 1;
    }
}
