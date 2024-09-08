package com.sieunp06.customife.repository;

import com.sieunp06.customife.domain.Category;
import com.sieunp06.customife.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
    Optional<Category> findByName(String name);
    Optional<Category> findByUserAndName(User user, String name);
}
