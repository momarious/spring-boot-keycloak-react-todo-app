package com.momarious.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.momarious.todoapi.entity.Todo;
import com.momarious.todoapi.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private KeycloakService keycloakService;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(String id) {
        return todoRepository.findById(id);
    }

    public List<Todo> getTodosByUserId(String userId) {
        return todoRepository.findByUserId(userId);
    }

    public List<Todo> getTodosByCategoryId(String categoryId) {
        return todoRepository.findByCategoryId(categoryId);
    }

    public Todo createTodo(Todo todo) {
        // todo.setUserId(keycloakService.getCurrentUserId());

        // Get the currently authenticated user
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = keycloakService.getCurrentUserId();

        // Verify that the session user is the same as the user in the todo data
        if (!todo.getUserId().equals(userId)) {
            // throw new UnauthorizedAccessException("You are not authorized to create this todo");
        }

        return todoRepository.save(todo);
    }

    public Optional<Todo> updateTodoById(String id, Todo todo) {
        Optional<Todo> existingTodo = todoRepository.findById(id);
        if (existingTodo.isPresent()) {
            // Copy the fields from the updated todo to the existing todo
            existingTodo.get().setTitle(todo.getTitle());
            existingTodo.get().setDescription(todo.getDescription());
            existingTodo.get().setStatus(todo.getStatus());
            existingTodo.get().setDueDate(todo.getDueDate());
            existingTodo.get().setCategoryId(todo.getCategoryId());

            // Save the updated todo to the database
            return Optional.of(todoRepository.save(existingTodo.get()));
        } else {
            return Optional.empty();
        }
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
