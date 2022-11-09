package com.Jyoti.blog.BlogappApi.Payloads;

import com.Jyoti.blog.BlogappApi.Entities.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private Set<CommentDto> comments = new HashSet<>();
}
