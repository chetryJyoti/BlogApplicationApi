package com.Jyoti.blog.BlogappApi.Controllers;

import com.Jyoti.blog.BlogappApi.Payloads.ApiResponse;
import com.Jyoti.blog.BlogappApi.Payloads.UserDto;
import com.Jyoti.blog.BlogappApi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //creating users
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //getting all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
       return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //getting user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


    //updating a user by id(PUT)
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updatedUser = this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }


    //ADMIN
    //deleting a user by id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>
                (new ApiResponse("Successfully deleted user",true),HttpStatus.OK);
    }

}
