package com.sieunp06.customife.repository;

import com.sieunp06.customife.domain.Milestone;
import com.sieunp06.customife.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findByUser(User user);
    Optional<Milestone> findByName(String name);
    Optional<Milestone> findByUserAndName(User user, String name);
}
