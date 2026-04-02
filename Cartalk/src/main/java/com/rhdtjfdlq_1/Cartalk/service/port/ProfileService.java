package com.rhdtjfdlq_1.Cartalk.service.port;

import com.rhdtjfdlq_1.Cartalk.dto.RequestProfileDto;

public interface ProfileService {
    String updateProfile(String email, RequestProfileDto request);
}