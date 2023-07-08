package ru.practicum.ewn.service.events;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewn.service.events.dto.EventDto;
import ru.practicum.ewn.service.events.dto.UpdateEventAdminRequest;
import ru.practicum.ewn.service.events.service.AdminEventService;
import ru.practicum.ewn.service.utils.AdminEventFilter;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/admin/events")
@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class AdminEventController {
    private final AdminEventService adminEventService;

    @GetMapping
    public List<EventDto> getAllEvents(@Valid @ModelAttribute("userEventFilter") AdminEventFilter adminEventFilter,
                                       @RequestParam(name = "from", defaultValue = "0", required = false) Integer from,
                                       @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        return adminEventService.adminGetEvents(adminEventFilter, from, size);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDto updateEvent(@PathVariable Long eventId,
                                @Valid @RequestBody UpdateEventAdminRequest request) {
        return adminEventService.updateEventByAdmin(eventId, request);
    }

    @PutMapping("{eventId}")
    public EventDto updateEventAdmin(@PathVariable Long eventId,
                                     @Valid @RequestBody UpdateEventAdminRequest request) {
        log.info("Администратором обновлено событие c id {}", eventId);
        return adminEventService.updateEventByAdmin(eventId, request);
    }

}
