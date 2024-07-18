package com.sieunp06.customife.controller;

import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.event.EventAddDto;
import com.sieunp06.customife.dto.response.event.EventResponseDto;
import com.sieunp06.customife.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @PostMapping("/add")
    public ResponseEntity<EventResponseDto> add(
            @RequestBody EventAddDto eventAddDto,
            @AuthenticationPrincipal User user) {
        EventResponseDto eventResponseDto = eventService.addEvent(eventAddDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponseDto);
    }
}
