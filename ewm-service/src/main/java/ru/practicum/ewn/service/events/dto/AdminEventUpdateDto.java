package ru.practicum.ewn.service.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewn.service.category.dto.CategoryDto;
import ru.practicum.ewn.service.enums.EventState;
import ru.practicum.ewn.service.events.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminEventUpdateDto {
    @NotBlank
    @Size(min = 20, max = 2000)
    String annotation;
    CategoryDto category;
    @Size(min = 20, max = 7000)
    String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    Location location;
    Boolean paid = false;
    Integer participantLimit = 0;
    Boolean requestModeration = true;
    EventState eventState;

    @NotBlank
    @Size(min = 3, max = 120)
    String title;
}
