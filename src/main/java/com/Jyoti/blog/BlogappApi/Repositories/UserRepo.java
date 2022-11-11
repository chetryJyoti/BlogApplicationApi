package com.Jyoti.blog.BlogappApi.Repositories;

import com.Jyoti.blog.BlogappApi.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//to talk to the database
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
