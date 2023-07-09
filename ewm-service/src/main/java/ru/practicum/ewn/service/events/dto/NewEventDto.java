package ru.practicum.ewn.service.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewn.service.events.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
public class NewEventDto {

    @NotNull
    @NotBlank(message = "Краткое описание события не может быть пустым")
    @Size(max = 2000, min = 20)
    private String annotation;

    @NotNull
    private Long category;

    @NotBlank(message = "Описание события не может быть пустым")
    @Size(max = 7000, min = 20)
    private String description;

    @NotNull
    private Location location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime eventDate;

    private Boolean paid;

    private Integer participantLimit;

    private Boolean requestModeration;

    @NotNull
    @Size(min = 3, max = 120)
    private String title;
}
