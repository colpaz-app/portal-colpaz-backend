package com.colpaz.colpaz.userAccount.services;

import com.colpaz.colpaz.userAccount.UserAccountRequest;
import com.colpaz.colpaz.userAccount.UserAccountResponse;

public interface UserAccountService  {
    UserAccountResponse createUser(UserAccountRequest request);
}