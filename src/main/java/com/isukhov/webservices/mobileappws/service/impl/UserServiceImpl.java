package com.isukhov.webservices.mobileappws.service.impl;

import com.isukhov.webservices.mobileappws.model.User;
import com.isukhov.webservices.mobileappws.repository.UserRepository;
import com.isukhov.webservices.mobileappws.service.UserService;
import com.isukhov.webservices.mobileappws.shared.Utils;
import com.isukhov.webservices.mobileappws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setUserId(utils.generateUserId(30));

        User storedUser = userRepository.save(user);
        UserDto returnedUser = new UserDto();
        BeanUtils.copyProperties(storedUser, returnedUser);

        return returnedUser;
    }
}
