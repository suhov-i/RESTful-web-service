package com.isukhov.webservices.mobileappws.service;

import com.isukhov.webservices.mobileappws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
}
