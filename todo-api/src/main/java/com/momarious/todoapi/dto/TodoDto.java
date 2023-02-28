package com.momarious.todoapi.dto;

import lombok.Data;

@Data
public class TodoDto {
    private String title;
    private String description;
    private String status;
    private String dueDate;   
}
