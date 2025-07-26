package com.colpaz.colpaz.auth;

import com.colpaz.colpaz.auth.dto.AuthRequest;
import com.colpaz.colpaz.auth.dto.AuthResponse;
import com.colpaz.colpaz.customUser.CustomUserDetails;
import com.colpaz.colpaz.userAccount.UserAccount;
import com.colpaz.colpaz.userAccount.UserAccountMapper;
import com.colpaz.colpaz.userAccount.UserAccountResponse;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserAccountMapper userAccountMapper;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserAccountMapper userAccountMapper, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userAccountMapper = userAccountMapper;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.generateToken(request.getUsername());
        String refreshToken = jwtUtils.generateRefreshToken(request.getUsername());

        CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();
        UserAccount user = customUser.getUserAccount();
        UserAccountResponse response = userAccountMapper.toResponse(user);

        return new AuthResponse(accessToken, refreshToken, response);
    }

    @PostMapping("/refresh-token")
    public AuthResponse refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        if (!jwtUtils.validateToken(refreshToken)) {
            throw new JwtException("Refresh token inválido o expirado");
        }

        String username = jwtUtils.getUsernameFromToken(refreshToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        CustomUserDetails customUser = (CustomUserDetails) userDetails;
        UserAccount user = customUser.getUserAccount();
        UserAccountResponse userResponse = userAccountMapper.toResponse(user);

        String newAccessToken = jwtUtils.generateToken(username);
        String newRefreshToken = jwtUtils.generateRefreshToken(username);

        return new AuthResponse(newAccessToken, newRefreshToken, userResponse);
    }
}