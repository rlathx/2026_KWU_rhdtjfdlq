package com.rhdtjfdlq_1.Cartalk.service.port;

import com.rhdtjfdlq_1.Cartalk.dto.RequestAccountDto;

public interface AccountService {
    String updateMyInfo(String email, RequestAccountDto request);
}