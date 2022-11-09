package com.Jyoti.blog.BlogappApi.Repositories;

import com.Jyoti.blog.BlogappApi.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
