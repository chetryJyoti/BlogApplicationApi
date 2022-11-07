package com.Jyoti.blog.BlogappApi.Repositories;

import com.Jyoti.blog.BlogappApi.Entities.Category;
import com.Jyoti.blog.BlogappApi.Entities.Post;
import com.Jyoti.blog.BlogappApi.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    //Custom methods

    //finding all posts by a user
    List<Post> findByUser(User user);

    //finding all post in a category
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);


}
