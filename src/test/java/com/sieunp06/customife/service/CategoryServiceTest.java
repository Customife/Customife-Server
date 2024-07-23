package com.sieunp06.customife.service;

import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@DisplayName("[CategoryService Test]")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void getCategory() throws Exception {
        // given
        User user = createUser();

        given(categoryRepository.findByUser(any(User.class)));

        // when

        // then
    }

    private static User createUser() {
        return User.builder()
                .userEmail("test@email.com")
                .userPassword("encodedPassword")
                .userNickName("testNickName")
                .build();
    }
}
