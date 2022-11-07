package com.Jyoti.blog.BlogappApi.Controllers;

import com.Jyoti.blog.BlogappApi.Entities.Post;
import com.Jyoti.blog.BlogappApi.Payloads.ApiResponse;
import com.Jyoti.blog.BlogappApi.Payloads.PostDto;
import com.Jyoti.blog.BlogappApi.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    //create posts
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
            ){
        PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    //get posts by userid
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(
            @PathVariable Integer userId)
    {
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    //get posts by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(
            @PathVariable Integer categoryId)
    {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    //get all post
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize
    ){
        List<PostDto> allPosts = this.postService.getAllPosts(pageNumber,pageSize);
        return new ResponseEntity<>(allPosts,HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto onePost = this.postService.getPostById(postId);
        return new ResponseEntity<>(onePost,HttpStatus.OK);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId,@RequestBody PostDto postDto){
       PostDto updatedPost=  this.postService.updatePost(postDto,postId);
       return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully",true),HttpStatus.OK);
    }


}
