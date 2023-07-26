package ru.practicum.ewn.service.compilations.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewn.service.compilations.dao.CompilationRepository;
import ru.practicum.ewn.service.compilations.dto.CompilationDto;
import ru.practicum.ewn.service.compilations.dto.CompilationDtoCreate;
import ru.practicum.ewn.service.compilations.dto.CompilationUpdateDto;
import ru.practicum.ewn.service.compilations.mapper.CompilationMapper;
import ru.practicum.ewn.service.compilations.model.Compilation;
import ru.practicum.ewn.service.events.dao.EventRepository;
import ru.practicum.ewn.service.events.model.Event;
import ru.practicum.ewn.service.handlers.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository compilationRepository;
    private final CompilationMapper compilationMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CompilationDto createCompilation(CompilationDtoCreate compilationDto) {
        log.info("creating new compilation {}", compilationDto);

        compilationDto.setPinned(compilationDto.getPinned() != null ? compilationDto.getPinned() : false);

        if (compilationDto.getEvents() == null) {
            compilationDto.setEvents(new ArrayList<>());
        }

        List<Event> events = eventRepository.findEventsByIdIn(compilationDto.getEvents());

        Compilation compilation = compilationMapper.toEntity(compilationDto, events);

        if (compilationDto.getEvents() != null) {
            compilation.setEvents(compilationDto.getEvents().stream()
                    .flatMap(ids -> eventRepository.findAllById(Collections.singleton(ids)).stream())
                    .collect(Collectors.toList()));
        } else {
            compilation.setEvents(new ArrayList<>());
        }

        compilationRepository.save(compilation);

        return compilationMapper.toDto(compilation);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CompilationDto updateCompilation(Long compilationId, CompilationUpdateDto compilationDto) {
        log.info("updating compilation with id {}", compilationId);
        Compilation compilation = getCompilationIfExists(compilationId);

        if (compilationDto.getEvents() != null) {
            compilation.setEvents(compilationDto.getEvents().stream()
                    .flatMap(ids -> eventRepository.findAllById(Collections.singleton(ids)).stream())
                    .collect(Collectors.toList()));
        }

        compilation.setPinned(compilationDto.getPinned() != null ? compilationDto.getPinned() : compilation.getPinned());
        compilation.setTitle(compilationDto.getTitle() != null ? compilationDto.getTitle() : compilation.getTitle());

        return compilationMapper.toDto(compilation);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteCompilation(Long compilationId) {
        log.info("deleting compilation with id {}", compilationId);
        compilationRepository.deleteById(compilationId);
    }

    @Override
    @Transactional(readOnly = true)
    public CompilationDto getCompilationById(Long compilationId) {
        log.info("getting compilation by id {}", compilationId);
        Compilation compilation = compilationRepository.findById(compilationId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Compilation with id %d not found", compilationId)));

        return compilationMapper.toDto(compilation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompilationDto> findAllCompilations(Boolean pinned, int from, int size) {
        log.info("getting compilations with filter by 'pinned'");
        Pageable pageable = PageRequest.of(from / size, size);

        if (pinned != null) {
            return compilationRepository.findCompilationByPinned(pinned, pageable).stream()
                    .map(compilationMapper::toDto)
                    .collect(Collectors.toList());
        }
        return compilationRepository.findAll(pageable).map(compilationMapper::toDto).toList();
    }

    private Compilation getCompilationIfExists(Long compilationId) {
        return compilationRepository.findById(compilationId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("Compilation with id %d not found", compilationId)));
    }
}