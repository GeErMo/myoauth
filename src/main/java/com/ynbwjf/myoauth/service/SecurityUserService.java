package com.ynbwjf.myoauth.service;

import com.ynbwjf.myoauth.entity.SecurityUser;

public interface SecurityUserService {
    SecurityUser selectUserByUserName(String username);
}
