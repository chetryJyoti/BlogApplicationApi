package com.Jyoti.blog.BlogappApi.Services;

import com.Jyoti.blog.BlogappApi.Entities.Comment;
import com.Jyoti.blog.BlogappApi.Payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
