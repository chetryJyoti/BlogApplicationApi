package com.Jyoti.blog.BlogappApi.Controllers;

import com.Jyoti.blog.BlogappApi.Entities.Comment;
import com.Jyoti.blog.BlogappApi.Payloads.ApiResponse;
import com.Jyoti.blog.BlogappApi.Payloads.CommentDto;
import com.Jyoti.blog.BlogappApi.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){
        CommentDto createComment = this.commentService.createComment(comment,postId);
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> createComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Deleted comment successfully",true), HttpStatus.CREATED);
    }
}
