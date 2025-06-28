package com.colpaz.colpaz.userAccount;

import com.colpaz.colpaz.role.RoleResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountResponse {
    private Long id;
    private String username;
    private boolean isActive;
    private String createdBy;
    private RoleResponse role;
}