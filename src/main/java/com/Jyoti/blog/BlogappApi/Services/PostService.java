package com.Jyoti.blog.BlogappApi.Services;

import com.Jyoti.blog.BlogappApi.Entities.Post;
import com.Jyoti.blog.BlogappApi.Payloads.PostDto;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    Post updatePost(PostDto postDto,Integer postId);

    //get all posts
    List<Post> getAllPosts();

    //get single post
    Post getPostById(Integer postId);

    //delete post
    void deletePost(Integer postId);

    //get all posts by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    //search posts
    List<Post> searchPosts(String keyword);
}
