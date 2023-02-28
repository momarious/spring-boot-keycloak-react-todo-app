package com.momarious.todoapi.controller;

import org.springframework.web.bind.annotation.*;

import com.momarious.todoapi.dto.ResponseDto;
import com.momarious.todoapi.dto.TodoDto;
import com.momarious.todoapi.entity.Todo;
import com.momarious.todoapi.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("todos")
public class TodoController {

    private final TodoService todoService;
    
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseDto<List<Todo>> getAllTodos() {
        try {
            return ResponseDto.success("Todos retrieved successfully", todoService.getAllTodos());
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

    @PostMapping
    public ResponseDto<Todo> createTodo(@RequestBody TodoDto todoDto) {
        try {
            return ResponseDto.success("Todo created successfully", todoService.createTodo(todoDto));
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseDto<Todo> getTodoById(@PathVariable String id) {
        try {
            return ResponseDto.success("Todo retrived successfully", todoService.getTodoById(id));
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseDto<Todo> updateTodo(@PathVariable String id, @RequestBody Todo todo) {
        try {
            return ResponseDto.success("Todo updated successfully", null);
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Boolean> deleteTodo(@PathVariable String id) {
        try {
            return ResponseDto.success("Todo deleted successfully", todoService.deleteTodoById(id));
        } catch (Exception e) {
            return ResponseDto.error(e.getMessage());
        }
    }
}
