package com.accordiq.security.jwt;

import com.accordiq.user.entity.User;

public interface JwtService {

    String generateToken(User user);

    String generateRefreshToken(User user);

    String extractUsername(String token);

    boolean isTokenValid(String token, User user);
}