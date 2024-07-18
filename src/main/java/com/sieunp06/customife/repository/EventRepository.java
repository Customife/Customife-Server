package com.sieunp06.customife.repository;

import com.sieunp06.customife.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
