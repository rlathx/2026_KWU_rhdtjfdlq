package com.rhdtjfdlq_1.Cartalk.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이메일 (로그인 ID)
    @Column(nullable = false, unique = true)
    private String email;

    // 암호화된 비밀번호 저장
    @Column(nullable = false)
    private String password;

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // 이름
    @Column(name = "name")
    private String name;

    // 닉네임
    @Column(name = "nick_name")
    private String nickName;

    // 전화번호
    @Column(name = "phone_num")
    private String phoneNum;

    // 상태 메시지
    @Column(name = "message")
    private String message;

    // 프로필 사진 URL
    @Column(name = "profile")
    private String profile;

    // 계정 생성 시간
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 계정 수정 시간
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void updateMyInfo(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public void updateProfile(String nickName, String message, String profile) {
        this.nickName = nickName;
        this.message = message;
        this.profile = profile;
    }
}
