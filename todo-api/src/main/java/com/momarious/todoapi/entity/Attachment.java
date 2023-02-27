package com.momarious.todoapi.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "attachments")
public class Attachment {
    @Id
    private String id;
    private String filename;
    private String fileType;
    private String todoId;
}
