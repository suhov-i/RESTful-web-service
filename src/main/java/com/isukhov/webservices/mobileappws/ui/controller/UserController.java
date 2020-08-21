package com.isukhov.webservices.mobileappws.ui.controller;

import com.isukhov.webservices.mobileappws.exceptions.UserServiceException;
import com.isukhov.webservices.mobileappws.service.UserService;
import com.isukhov.webservices.mobileappws.shared.dto.UserDto;
import com.isukhov.webservices.mobileappws.ui.model.request.UserDetails;
import com.isukhov.webservices.mobileappws.ui.model.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("users")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
//    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public UserRest getUser(@PathVariable String id) {
        UserRest result = new UserRest();

        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, result);

        return result;
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetails userDetails) throws Exception {
        UserRest result = new UserRest();

        if (userDetails.getFirstName().isEmpty()) {
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, result);

        return result;
    }

    @PutMapping(path = "/{userId}")
    public UserRest updateUser(@PathVariable String userId, @RequestBody UserDetails userDetails) {
        UserRest result = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updatedUser = userService.updateUser(userId, userDto);
        BeanUtils.copyProperties(updatedUser, result);

        return result;
    }

    @DeleteMapping(path = "/{userId}")
    public OperationStatus deleteUser(@PathVariable String userId) {
        OperationStatus operationStatus = new OperationStatus();
        operationStatus.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(userId);

        operationStatus.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return operationStatus;
    }
}
