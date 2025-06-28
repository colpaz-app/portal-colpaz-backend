package com.colpaz.colpaz.userAccount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountRequest {
    private String username;
    private String password;
    private Long roleId;
    private boolean isActive;
    private String createdBy;
}