package com.accordiq.auth.service;

import com.accordiq.auth.dto.AuthResponse;
import com.accordiq.auth.dto.LoginRequest;
import com.accordiq.auth.dto.RefreshTokenRequest;
import com.accordiq.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);
}