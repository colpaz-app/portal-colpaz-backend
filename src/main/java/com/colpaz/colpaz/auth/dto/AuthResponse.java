package com.colpaz.colpaz.auth.dto;

import com.colpaz.colpaz.userAccount.UserAccountResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String refreshToken;
    private UserAccountResponse user;
}