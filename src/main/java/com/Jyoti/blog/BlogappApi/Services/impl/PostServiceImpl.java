package com.Jyoti.blog.BlogappApi.Services.impl;

import com.Jyoti.blog.BlogappApi.Entities.Category;
import com.Jyoti.blog.BlogappApi.Entities.Post;
import com.Jyoti.blog.BlogappApi.Entities.User;
import com.Jyoti.blog.BlogappApi.Exceptions.ResourceNotFoundException;
import com.Jyoti.blog.BlogappApi.Payloads.PostDto;
import com.Jyoti.blog.BlogappApi.Payloads.PostResponse;
import com.Jyoti.blog.BlogappApi.Repositories.CategoryRepo;
import com.Jyoti.blog.BlogappApi.Repositories.PostRepo;
import com.Jyoti.blog.BlogappApi.Repositories.UserRepo;
import com.Jyoti.blog.BlogappApi.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageLink(postDto.getImageLink());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy) {

        Pageable p =  PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> posts = pagePost.getContent();
//        List<Post> posts = this.postRepo.findAll();
        List<PostDto> allPosts = posts.stream().map((post) ->this.modelMapper.map(post,PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(allPosts);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        PostDto postDto = this.modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        if(post != null){
            this.postRepo.delete(post);
        }
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
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts =this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((post) ->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
