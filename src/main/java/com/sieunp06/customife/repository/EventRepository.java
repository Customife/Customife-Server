package com.sieunp06.customife.repository;

import com.sieunp06.customife.domain.Event;
import com.sieunp06.customife.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUser(User user);
}
