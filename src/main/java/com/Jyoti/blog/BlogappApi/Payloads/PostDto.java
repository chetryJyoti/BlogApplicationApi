package com.Jyoti.blog.BlogappApi.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer postId;

    private String title;

    private String content;

    private String imageLink;

    private Date createdOn;

    private CategoryDto category;

    private UserDto user;

}
