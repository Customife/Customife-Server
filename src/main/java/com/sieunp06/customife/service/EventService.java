package com.sieunp06.customife.service;

import com.sieunp06.customife.domain.Category;
import com.sieunp06.customife.domain.Event;
import com.sieunp06.customife.domain.Milestone;
import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.event.EventAddDto;
import com.sieunp06.customife.dto.request.event.EventUpdateDto;
import com.sieunp06.customife.dto.response.event.EventResponseDto;
import com.sieunp06.customife.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    private final CategoryService categoryService;
    private final MilestoneService milestoneService;

    public List<EventResponseDto> getEvents(User user) {
        return eventRepository.findByUser(user).stream()
                .map(EventResponseDto::from)
                .collect(Collectors.toList());
    }

    public EventResponseDto addEvent(EventAddDto eventAddDto, User user) {
        Category category = categoryService.findCategory(eventAddDto.getCategory());
        Milestone milestone = milestoneService.findMilestone(eventAddDto.getMilestone());
        Event event = eventRepository.save(Event.builder()
                        .user(user)
                        .category(category)
                        .milestone(milestone)
                        .content(eventAddDto.getContent())
                        .startDate(eventAddDto.getStartDate())
                        .endDate(eventAddDto.getEndDate())
                        .memo(eventAddDto.getMemo())
                        .build());
        return EventResponseDto.from(event);
    }

    public EventResponseDto updateEvent(User user, Long eventId, EventAddDto eventAddDto) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(IllegalArgumentException::new);
        validateUser(user, event.getUser());
        EventUpdateDto eventUpdateDto = EventUpdateDto.builder()
                .category(categoryService.findCategory(eventAddDto.getCategory()))
                .milestone(milestoneService.findMilestone(eventAddDto.getMilestone()))
                .content(eventAddDto.getContent())
                .startDate(eventAddDto.getStartDate())
                .endDate(eventAddDto.getEndDate())
                .build();
        event.update(eventUpdateDto);
        return EventResponseDto.from(event);
    }

    public void deleteEvent(User user, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(IllegalArgumentException::new);
        validateUser(user, event.getUser());
        eventRepository.delete(event);
    }

    private void validateUser(User user1, User user2) {
        if (!user1.equals(user2)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
}
