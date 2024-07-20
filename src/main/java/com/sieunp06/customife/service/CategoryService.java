package com.sieunp06.customife.service;

import com.sieunp06.customife.domain.Category;
import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.category.CategoryDto;
import com.sieunp06.customife.dto.response.category.CategoryResponseDto;
import com.sieunp06.customife.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getCategory(User user) {
        return categoryRepository.findByUser(user).stream()
                .map(CategoryResponseDto::from)
                .collect(Collectors.toList());
    }

    public CategoryResponseDto getDetailCategory(Long categoryId, User user) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(IllegalStateException::new);
        validateUser(category.getUser(), user);
        return CategoryResponseDto.from(category);
    }

    public CategoryResponseDto addCategory(CategoryDto categoryDto, User user) {
        validateDuplicatedName(categoryDto.getName());
        Category category = categoryRepository.save(Category.builder()
                        .user(user)
                        .name(categoryDto.getName())
                        .colorCode(categoryDto.getColorCode())
                        .build());
        return CategoryResponseDto.from(category);
    }

    public CategoryResponseDto updateCategory(Long categoryId, CategoryDto categoryDto, User user) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(IllegalStateException::new);
        validateUser(category.getUser(), user);
        category.update(categoryDto.getName(), categoryDto.getColorCode());
        return CategoryResponseDto.from(category);
    }

    public void deleteCategory(Long categoryId, User user) {
        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(IllegalArgumentException::new);
        validateUser(category.getUser(), user);
        categoryRepository.delete(category);
    }

    private void validateDuplicatedName(String name) {
        categoryRepository.findByName(name)
                .ifPresent(error -> {
                    throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
                });
    }

    private void validateUser(User user1, User user2) {
        if (!user1.equals(user2)) {
            throw new IllegalStateException("권한이 없습니다.");
        }
    }
}
