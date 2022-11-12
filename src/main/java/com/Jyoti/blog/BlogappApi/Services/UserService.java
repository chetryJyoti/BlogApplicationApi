package com.Jyoti.blog.BlogappApi.Services;

import com.Jyoti.blog.BlogappApi.Entities.User;
import com.Jyoti.blog.BlogappApi.Payloads.UserDto;

import java.util.List;

public interface UserService {


    UserDto registerNewUser(UserDto user);
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

}
