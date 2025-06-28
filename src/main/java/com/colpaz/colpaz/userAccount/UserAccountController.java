package com.colpaz.colpaz.userAccount;

import com.colpaz.colpaz.userAccount.services.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping
    public ResponseEntity<UserAccountResponse> createUser(@Valid @RequestBody UserAccountRequest request) {
        UserAccountResponse response = userAccountService.createUser(request);
        return ResponseEntity.ok(response);
    }
}