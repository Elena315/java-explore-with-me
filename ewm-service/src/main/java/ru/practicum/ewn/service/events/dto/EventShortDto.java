package ru.practicum.ewn.service.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewn.service.category.dto.CategoryDto;
import ru.practicum.ewn.service.users.dto.UserShortDto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventShortDto {
    @NotBlank
    String annotation;

    CategoryDto category;

    Long confirmedRequests;

    @NotBlank
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    Long id;

    @NotNull
    UserShortDto initiator;

    @NotNull
    Boolean paid;

    @NotBlank
    String title;

    Integer views;
}
