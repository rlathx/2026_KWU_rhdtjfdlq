package com.rhdtjfdlq_1.Cartalk.service.impl;

import com.rhdtjfdlq_1.Cartalk.dto.RequestAccountDto;
import com.rhdtjfdlq_1.Cartalk.entity.UserEntity;
import com.rhdtjfdlq_1.Cartalk.repository.UserRepository;
import com.rhdtjfdlq_1.Cartalk.service.port.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public String updateMyInfo(String email, RequestAccountDto request) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));

        String phoneNumber = request.getPhoneNumber();

        if (!phoneNumber.matches("^010-\\d{4}-\\d{4}$")) {
            throw new RuntimeException("INVALID_PHONE_NUMBER");
        }

        user.updateMyInfo(request.getName(), phoneNumber);

        return "내 정보가 설정 되었습니다.";
    }
}