package com.rhdtjfdlq_1.Cartalk.service.port;

import com.rhdtjfdlq_1.Cartalk.dto.RequestLoginDto;
import com.rhdtjfdlq_1.Cartalk.dto.ResponseLoginDto;

public interface LoginService {
    ResponseLoginDto login(RequestLoginDto request);
}