package com.sieunp06.customife.controller;

import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.category.CategoryDto;
import com.sieunp06.customife.dto.response.category.CategoryResponseDto;
import com.sieunp06.customife.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategory(user));
    }

    @GetMapping("{categoryId}/detail")
    public ResponseEntity<CategoryResponseDto> getDetailCategory(
            @PathVariable Long categoryId,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getDetailCategory(categoryId, user));
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> addCategory(
            @RequestBody CategoryDto categoryDto,
            @AuthenticationPrincipal User user) {
        CategoryResponseDto categoryResponseDto = categoryService.addCategory(categoryDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponseDto);
    }

    @PatchMapping("/{categoryId}/update")
    public ResponseEntity<CategoryResponseDto> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryDto categoryDto,
            @AuthenticationPrincipal User user) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryId, categoryDto, user);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDto);
    }

    @DeleteMapping("{categoryId}/delete")
    public ResponseEntity<Long> deleteCategory(@PathVariable Long categoryId, @AuthenticationPrincipal User user) {
        categoryService.deleteCategory(categoryId, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
