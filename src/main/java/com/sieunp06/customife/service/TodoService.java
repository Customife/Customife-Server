package com.sieunp06.customife.service;

import com.sieunp06.customife.domain.Category;
import com.sieunp06.customife.domain.Milestone;
import com.sieunp06.customife.domain.Todo;
import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.todo.TodoDto;
import com.sieunp06.customife.dto.response.todo.TodoResponseDto;
import com.sieunp06.customife.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final CategoryService categoryService;
    private final MilestoneService milestoneService;

    public List<TodoResponseDto> getTodos(User user) {
        return todoRepository.findByUser(user).stream()
                .map(TodoResponseDto::from)
                .collect(Collectors.toList());
    }

    public TodoResponseDto addTodo(TodoDto todoDto, User user) {
        Category category = categoryService.findCategory(todoDto.getCategory());
        Milestone milestone = milestoneService.findMilestone(todoDto.getMilestone());
        Todo todo = todoRepository.save(Todo.builder()
                        .user(user)
                        .category(category)
                        .milestone(milestone)
                        .content(todoDto.getContent())
                        .date(todoDto.getDate())
                        .build());
        return TodoResponseDto.from(todo);
    }

    public void deleteTodo(Long todoId, User user) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(IllegalArgumentException::new);
        validateUser(user, todo.getUser());
        todoRepository.delete(todo);
    }

    public void turnCompleted(Long todoId, User user) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(IllegalArgumentException::new);
        validateUser(user, todo.getUser());
        todo.changeCompleted();
    }

    private void validateUser(User user1, User user2) {
        if (!user1.equals(user2)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
}
