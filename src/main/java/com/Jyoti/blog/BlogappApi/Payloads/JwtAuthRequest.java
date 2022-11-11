package com.Jyoti.blog.BlogappApi.Payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
