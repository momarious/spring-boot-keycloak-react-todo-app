package com.momarious.todoapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.momarious.todoapi.entity.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
    
}
