package ru.practicum.ewn.service.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.dto.EndpointHitDto;
import ru.practicum.ewn.service.events.dao.CommentRepository;
import ru.practicum.ewn.service.events.dao.EventRepository;
import ru.practicum.ewn.service.events.dto.CommentDtoResponse;
import ru.practicum.ewn.service.events.dto.EventDto;
import ru.practicum.ewn.service.events.dto.EventShortDto;
import ru.practicum.ewn.service.events.mapper.CommentMapper;
import ru.practicum.ewn.service.events.mapper.EventMapper;
import ru.practicum.ewn.service.events.model.Event;
import ru.practicum.ewn.service.handlers.DataException;
import ru.practicum.ewn.service.handlers.NotFoundException;
import ru.practicum.ewn.service.statistic.StatisticService;
import ru.practicum.ewn.service.utils.UserEventFilter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.practicum.ewn.service.utils.UserEventSpecification.filtersFromUser;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final StatisticService statisticService;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EventShortDto> publicGetEvents(UserEventFilter filter,
                                               int from,
                                               int size,
                                               EndpointHitDto endpointHitDto) {
        log.info("getting all events by users");
        statisticService.sendStatistic(endpointHitDto);
        Pageable pageable = PageRequest.of(from / size, size);
        Specification<Event> specification = filtersFromUser(filter);
        List<Event> events = eventRepository.findAll(specification, pageable);
        if (events == null || events.isEmpty()) {
            throw new DataException("Incorrectly made request.");
        }
        return buildEventResponse(events).stream()
                .map(eventMapper::toShortDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EventDto getEventById(Long id, EndpointHitDto endpointHitDto) {
        log.info("getting event with id {}", id);
        statisticService.sendStatistic(endpointHitDto);
        Event event = eventRepository.findByIdAndPublishedOnIsNotNull(id)
                .orElseThrow(() -> new NotFoundException(String.format("event with id %d not found", id)));

        return buildEventResponse(event);
    }

    @Override
    public List<CommentDtoResponse> getCommentsByEventId(Long eventId, int from, int size) {
        log.info("getting comments by event with id {}", eventId);
        Pageable pageable = PageRequest.of(from, size);

        return commentRepository.findCommentsByEventId(eventId, pageable).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }


    private List<EventDto> buildEventResponse(List<Event> events) {
        if (!events.isEmpty()) {
            Map<Long, Long> stats = buildStatisticMap(events);

            if (stats != null && !stats.isEmpty()) {
                return events.stream()
                        .map(event -> stats.containsKey(event.getId()) ?
                                eventMapper.toDtoWithViews(event, stats.get(event.getId())) :
                                eventMapper.toDto(event))
                        .collect(Collectors.toList());
            }

        }
        return events.stream().map(eventMapper::toDto).collect(Collectors.toList());
    }

    private EventDto buildEventResponse(Event event) {
        Map<Long, Long> stats = buildStatisticMap(List.of(event));

        if (stats != null && !stats.isEmpty()) {
            return stats.containsKey(event.getId()) ?
                    eventMapper.toDtoWithViews(event, stats.get(event.getId())) :
                    eventMapper.toDto(event);
        }

        return eventMapper.toDto(event);
    }

    private Map<Long, Long> buildStatisticMap(List<Event> events) {
        LocalDateTime startDate = events.stream()
                .map(Event::getCreatedOn)
                .min(LocalDateTime::compareTo).orElse(LocalDateTime.MIN);

        List<Long> eventIds = events.stream().map(Event::getId).collect(Collectors.toList());

        Map<Long, Long> map = new HashMap<>();

        statisticService.getStatistics(startDate, eventIds)
                .forEach(e ->
                        map.put(Long.valueOf(e.getUri().substring(e.getUri().length() - 1)), e.getHits()));

        return map;
    }
}