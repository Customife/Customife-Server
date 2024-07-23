package com.sieunp06.customife.controller;

import com.sieunp06.customife.domain.User;
import com.sieunp06.customife.dto.request.event.EventAddDto;
import com.sieunp06.customife.dto.response.event.EventResponseDto;
import com.sieunp06.customife.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @GetMapping("/get")
    public ResponseEntity<List<EventResponseDto>> getEvent(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEvents(user));
    }

    @PostMapping("/add")
    public ResponseEntity<EventResponseDto> addEvent(
            @RequestBody EventAddDto eventAddDto,
            @AuthenticationPrincipal User user) {
        EventResponseDto eventResponseDto = eventService.addEvent(eventAddDto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponseDto);
    }

    @PatchMapping("/{eventId}/update")
    public ResponseEntity<EventResponseDto> updateEvent(
            @AuthenticationPrincipal User user,
            @PathVariable Long eventId,
            @RequestBody EventAddDto eventAddDto) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.updateEvent(user, eventId, eventAddDto));
    }

    @DeleteMapping("/{eventId}/delete")
    public ResponseEntity<Long> deleteEvent(
            @AuthenticationPrincipal User user,
            @PathVariable Long eventId) {
        eventService.deleteEvent(user, eventId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
