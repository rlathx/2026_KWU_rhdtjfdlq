package com.rhdtjfdlq_1.Cartalk_BE.service.port;

import com.rhdtjfdlq_1.Cartalk_BE.dto.RequestLoginDto;
import com.rhdtjfdlq_1.Cartalk_BE.dto.ResponseLoginDto;

public interface LoginService {
    ResponseLoginDto login(RequestLoginDto request);
}