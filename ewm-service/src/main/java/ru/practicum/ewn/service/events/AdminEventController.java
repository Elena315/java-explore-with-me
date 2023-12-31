package ru.practicum.ewn.service.events;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewn.service.events.dto.CommentDtoResponse;
import ru.practicum.ewn.service.events.dto.EventDto;
import ru.practicum.ewn.service.events.dto.UpdateCommentAdminRequest;
import ru.practicum.ewn.service.events.dto.UpdateEventAdminRequest;
import ru.practicum.ewn.service.events.service.AdminEventService;
import ru.practicum.ewn.service.utils.AdminEventFilter;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
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

    @GetMapping("/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse getCommentById(@PathVariable Long commentId) {
        return adminEventService.getCommentById(commentId);
    }

    @PatchMapping("comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse updateCommentStatus(@PathVariable Long commentId,
                                                  @Valid @RequestBody UpdateCommentAdminRequest adminRequest) {
        return adminEventService.updateCommentByAdmin(commentId, adminRequest);
    }

}
