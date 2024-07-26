package com.sieunp06.customife.controller;

import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.todo.TodoDto;
import com.sieunp06.customife.dto.response.todo.TodoResponseDto;
import com.sieunp06.customife.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/get")
    public ResponseEntity<List<TodoResponseDto>> getTodos(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodos(user));
    }

    @PostMapping("/add")
    public ResponseEntity<TodoResponseDto> addTodo(
            @RequestBody TodoDto todoDto,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addTodo(todoDto, user));
    }

    @DeleteMapping("/{todoId}/delete")
    public ResponseEntity<Long> deleteTodo(
            @PathVariable Long todoId,
            @AuthenticationPrincipal User user) {
        todoService.deleteTodo(todoId, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{todoId}/change")
    public ResponseEntity<Long> changeComplete(
            @PathVariable Long todoId,
            @AuthenticationPrincipal User user) {
        todoService.turnCompleted(todoId, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
