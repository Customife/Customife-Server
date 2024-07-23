package com.sieunp06.customife.repository;

import com.sieunp06.customife.domain.Schedule;
import com.sieunp06.customife.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUser(User user);
}
