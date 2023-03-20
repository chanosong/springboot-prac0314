package com.practice.basic1.boundedContext.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Member {
    @Id // PK
    @GeneratedValue(strategy = IDENTITY) // AUTO_INCREMENT
    private long id;
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime modifyDate;
    private String username;
    private String password;
}
