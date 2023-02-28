package com.momarious.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momarious.todoapi.dto.TodoDto;
import com.momarious.todoapi.entity.Todo;
import com.momarious.todoapi.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(String id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public List<Todo> getTodosByUserId(String userId) {
        return todoRepository.findByUserId(userId);
    }

    public List<Todo> getTodosByCategoryId(String categoryId) {
        return todoRepository.findByCategoryId(categoryId);
    }

    public Todo createTodo(TodoDto todoDto) {

        // if (!todo.getUserId().equals(userId)) {
        //     throw new UnauthorizedAccessException("You are not authorized to create this todo");
        // }

        Todo todo = Todo.builder()
                .title(todoDto.getTitle())
                .description(todoDto.getDescription())
                .dueDate(todoDto.getDueDate())
                // Other properties can be set as well
                .build();

        return todoRepository.save(todo);
    }

    public Optional<TodoDto> updateTodoById(String id, TodoDto todo) {
        // Optional<Todo> existingTodo = todoRepository.findById(id);
        // if (existingTodo.isPresent()) {
        //     // Copy the fields from the updated todo to the existing todo
        //     existingTodo.get().setTitle(todo.getTitle());
        //     existingTodo.get().setDescription(todo.getDescription());
        //     existingTodo.get().setStatus(todo.getStatus());
        //     existingTodo.get().setDueDate(todo.getDueDate());
        //     existingTodo.get().setCategoryId(todo.getCategoryId());

        //     // Save the updated todo to the database
        //     return Optional.of(todoRepository.save(existingTodo.get()));
        // } else {
        //     return Optional.empty();
        // }
        return null;
    }

    public boolean deleteTodoById(String id) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if (existingTodo.isPresent()) {
            todoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
