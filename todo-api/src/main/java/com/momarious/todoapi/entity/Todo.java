package com.momarious.todoapi.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "todos")
public class Todo {
    @Id
    private String id;
    private String title;
    private String description;
    private String status;
    private String dueDate;
    private String userId;
    private String categoryId;
}