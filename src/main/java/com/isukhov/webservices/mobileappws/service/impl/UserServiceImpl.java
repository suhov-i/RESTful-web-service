package com.isukhov.webservices.mobileappws.service.impl;

import com.isukhov.webservices.mobileappws.model.UserEntity;
import com.isukhov.webservices.mobileappws.repository.UserRepository;
import com.isukhov.webservices.mobileappws.service.UserService;
import com.isukhov.webservices.mobileappws.shared.Utils;
import com.isukhov.webservices.mobileappws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(utils.generateUserId(30));

        UserEntity storedUserEntity = userRepository.save(userEntity);
        UserDto returnedUser = new UserDto();
        BeanUtils.copyProperties(storedUserEntity, returnedUser);

        return returnedUser;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
