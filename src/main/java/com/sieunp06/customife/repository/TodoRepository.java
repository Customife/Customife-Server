package com.sieunp06.customife.repository;

import com.sieunp06.customife.domain.Todo;
import com.sieunp06.customife.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findByUser(User user);
}
