package ru.practicum.ewn.service.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewn.service.events.model.Location;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
public class NewEventDto {
    @NotBlank
    @NotNull
    @Size(min = 20, max = 2000)
    private String annotation;
    @NotNull
    private Long category;
    @NotBlank
    @Size(min = 20, max = 7000)
    private String description;
    @NotNull
    private Location location;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime eventDate;
    @Builder.Default
    private Boolean paid = false;
    @PositiveOrZero
    @Builder.Default
    private Integer participantLimit = 0;
    @Builder.Default
    private Boolean requestModeration = true;
    @NotNull
    @Size(min = 3, max = 120)
    private String title;
}
