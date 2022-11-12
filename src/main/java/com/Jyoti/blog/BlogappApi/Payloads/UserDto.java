package com.Jyoti.blog.BlogappApi.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 4,message = "Username must be minimum 4 characters long")
    private String name;

    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty
    @Size(min = 4,max = 10,message = "password must be min 3 and max 10 char long")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
