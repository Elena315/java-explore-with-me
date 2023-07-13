package ru.practicum.ewn.service.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewn.service.category.dto.CategoryDto;
import ru.practicum.ewn.service.enums.EventState;
import ru.practicum.ewn.service.events.model.Location;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
public class AdminEventUpdateDto {
    @Size(min = 20, max = 2000)
    private String annotation;
    private CategoryDto category;
    @Size(min = 20, max = 7000)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private Location location;

    private Boolean paid = false;

    private Integer participantLimit = 0;

    private Boolean requestModeration = true;
    private EventState eventState;
    @Size(min = 3, max = 120)
    private String title;
}
