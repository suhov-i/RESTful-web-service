package com.isukhov.webservices.mobileappws.ui.controller;

import com.isukhov.webservices.mobileappws.service.UserService;
import com.isukhov.webservices.mobileappws.shared.dto.UserDto;
import com.isukhov.webservices.mobileappws.ui.model.request.UserDetails;
import com.isukhov.webservices.mobileappws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser() {
        return "get";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetails userDetails) {
        UserRest result = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, result);

        return result;
    }

    @PutMapping
    public String updateUser() {
        return "update";
    }

    @DeleteMapping
    public String deleteUser() {
        return "delete";
    }
}
