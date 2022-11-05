package com.Jyoti.blog.BlogappApi.Repositories;

import com.Jyoti.blog.BlogappApi.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
