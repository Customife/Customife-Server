package com.sieunp06.customife.service;

import com.sieunp06.customife.domain.Event;
import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.event.EventAddDto;
import com.sieunp06.customife.dto.response.event.EventResponseDto;
import com.sieunp06.customife.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public EventResponseDto addEvent(EventAddDto eventAddDto, User user) {
        Event event = eventRepository.save(Event.builder()
                        .user(user)
                        .category(eventAddDto.getCategory())
                        .milestone(eventAddDto.getMilestone())
                        .content(eventAddDto.getContent())
                        .startDate(eventAddDto.getStartDate())
                        .endDate(eventAddDto.getEndDate())
                        .memo(eventAddDto.getMemo())
                        .build());
        return EventResponseDto.from(event);
    }
}
