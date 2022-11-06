package com.Jyoti.blog.BlogappApi.Services.impl;

import com.Jyoti.blog.BlogappApi.Entities.Category;
import com.Jyoti.blog.BlogappApi.Entities.Post;
import com.Jyoti.blog.BlogappApi.Entities.User;
import com.Jyoti.blog.BlogappApi.Exceptions.ResourceNotFoundException;
import com.Jyoti.blog.BlogappApi.Payloads.PostDto;
import com.Jyoti.blog.BlogappApi.Repositories.CategoryRepo;
import com.Jyoti.blog.BlogappApi.Repositories.PostRepo;
import com.Jyoti.blog.BlogappApi.Repositories.UserRepo;
import com.Jyoti.blog.BlogappApi.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        //getting user
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));

        //getting category
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageLink("default.png");
        post.setCreatedOn(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post  newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        //find category by id
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        //fetch post by that category
        List<Post> posts = this.postRepo.findByCategory(category);
        //map Post to PostDto
        List<PostDto> postDtos = posts.stream().map((post) ->this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        //find user by id
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
        //find post by user
        List<Post> posts = this.postRepo.findByUser(user);
        //map posts to postdtos
        List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
