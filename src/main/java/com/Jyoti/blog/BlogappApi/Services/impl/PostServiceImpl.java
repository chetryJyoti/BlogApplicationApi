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
    public List<Post> getPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
