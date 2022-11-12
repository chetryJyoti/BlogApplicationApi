package com.Jyoti.blog.BlogappApi.Services.impl;

import com.Jyoti.blog.BlogappApi.Config.AppConstants;
import com.Jyoti.blog.BlogappApi.Entities.Role;
import com.Jyoti.blog.BlogappApi.Entities.User;
import com.Jyoti.blog.BlogappApi.Exceptions.ResourceNotFoundException;
import com.Jyoti.blog.BlogappApi.Payloads.UserDto;
import com.Jyoti.blog.BlogappApi.Repositories.RoleRepo;
import com.Jyoti.blog.BlogappApi.Repositories.UserRepo;
import com.Jyoti.blog.BlogappApi.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        //check if the user is already present
            User newUser = new User();

            User user1 = this.modelMapper.map(userDto,User.class);

            //encode user password
            user1.setPassword(this.passwordEncoder.encode(user1.getPassword()));
            //set user role
            Role role = this.roleRepo.findById(AppConstants.ROLE_NORMAL).get();
            user1.getRoles().add(role);
            newUser= this.userRepo.save(user1);

        return this.modelMapper.map(newUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user1 = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user1.setName(userDto.getName());
        user1.setEmail(userDto.getEmail());
        user1.setPassword(userDto.getPassword());
        user1.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user1);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User foundUser = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(foundUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users =  this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
       User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
       this.userRepo.delete(user);
    }


    //MANUALLY CONVERTING
    // dtoUser(from Payloads package) to User type
    public User dtoToUser(UserDto userDto){

        //converting using ModelMapper
        User user = this.modelMapper.map(userDto,User.class);

        //Manually converting
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setPassword(userDto.getPassword());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());

        return user;
    }


    //converting User to dtoUser type
    public UserDto userToDto(User user){

        UserDto userDto = this.modelMapper.map(user,UserDto.class);

//        UserDto userDto =new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setPassword(user.getPassword());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());

        return userDto;
    }

}
