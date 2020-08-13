package com.isukhov.webservices.mobileappws.service;

import com.isukhov.webservices.mobileappws.shared.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);
}
