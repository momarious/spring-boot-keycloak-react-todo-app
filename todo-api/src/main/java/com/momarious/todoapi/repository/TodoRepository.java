package com.momarious.todoapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.momarious.todoapi.entity.Todo;
import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {

    List<Todo> findByUserId(String userId);
    List<Todo> findByCategoryId(String categoryId);
}
