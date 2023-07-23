package ru.practicum.ewm.stat.service.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.EndpointHitDto;
import ru.practicum.ewm.dto.HitCriteria;
import ru.practicum.ewm.dto.ViewStatisticsDto;
import ru.practicum.ewm.stat.service.server.service.StatsService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatsController {
    private final StatsService statsService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public void addHit(@Valid @RequestBody EndpointHitDto endpointHitDto) {
        statsService.saveStatistics(endpointHitDto);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ViewStatisticsDto>> getStatistics(@Valid @ModelAttribute(name = "hitCriteria") HitCriteria hitCriteria) {
        log.info("received new request with hit criteria: {}", hitCriteria);

        if (hitCriteria.getStart().isAfter(hitCriteria.getEnd())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<ViewStatisticsDto> result = statsService.getStatistics(
                hitCriteria.getStart(),
                hitCriteria.getEnd(),
                Optional.ofNullable(hitCriteria.getUris()).orElse(List.of()),
                hitCriteria.isUnique()
        );
        return ResponseEntity.ok(result);
    }
}

