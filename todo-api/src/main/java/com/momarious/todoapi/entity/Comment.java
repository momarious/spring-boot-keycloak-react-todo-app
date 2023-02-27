package com.momarious.todoapi.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String text;
    private String author;
}
