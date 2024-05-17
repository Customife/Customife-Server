package com.sieunp06.customife.repository;

import com.sieunp06.customife.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
