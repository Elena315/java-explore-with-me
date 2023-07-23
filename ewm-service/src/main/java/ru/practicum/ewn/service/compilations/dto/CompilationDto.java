package ru.practicum.ewn.service.compilations.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewn.service.events.dto.EventShortDto;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
public class CompilationDto {
    private Long id;
    private List<EventShortDto> events;
    private Boolean pinned;
    @Size(max = 50)
    private String title;
}