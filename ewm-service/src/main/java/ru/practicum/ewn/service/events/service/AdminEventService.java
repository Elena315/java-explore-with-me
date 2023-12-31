package ru.practicum.ewn.service.events.service;

import ru.practicum.ewn.service.events.dto.CommentDtoResponse;
import ru.practicum.ewn.service.events.dto.EventDto;
import ru.practicum.ewn.service.events.dto.UpdateCommentAdminRequest;
import ru.practicum.ewn.service.events.dto.UpdateEventAdminRequest;
import ru.practicum.ewn.service.utils.AdminEventFilter;

import java.util.List;

public interface AdminEventService {
    List<EventDto> adminGetEvents(AdminEventFilter filter, int from, int size);

    EventDto updateEventByAdmin(Long eventId, UpdateEventAdminRequest updateRequest);

    CommentDtoResponse updateCommentByAdmin(Long commentId, UpdateCommentAdminRequest updateCommentAdminRequest);

    CommentDtoResponse getCommentById(Long commentId);
}
