package com.colpaz.colpaz.auth;

import com.colpaz.colpaz.auth.dto.AuthRequest;
import com.colpaz.colpaz.auth.dto.AuthResponse;
import com.colpaz.colpaz.customUser.CustomUserDetails;
import com.colpaz.colpaz.userAccount.UserAccount;
import com.colpaz.colpaz.userAccount.UserAccountMapper;
import com.colpaz.colpaz.userAccount.UserAccountResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserAccountMapper userAccountMapper;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserAccountMapper userAccountMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userAccountMapper = new UserAccountMapper();
    }

    @PostMapping("/login")
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(request.getUsername());

        CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();
        UserAccount user = customUser.getUserAccount();
        UserAccountResponse response = userAccountMapper.toResponse(user);

        return new AuthResponse(token, response);
    }
}