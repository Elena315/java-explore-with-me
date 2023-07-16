package ru.practicum.ewn.service.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import ru.practicum.ewn.service.category.dto.CategoryDto;
import ru.practicum.ewn.service.enums.EventState;
import ru.practicum.ewn.service.events.model.Location;
import ru.practicum.ewn.service.users.dto.UserShortDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
public class EventDto {
    @NotNull
    private Long id;
    @NotNull
    @Size(min = 20, max = 2000)
    private String annotation;
    private CategoryDto category;
    private Integer confirmedRequests;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    @Size(min = 20,max = 7000)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    private Location location;
    @Builder.Default
    private Boolean paid = false;
    @Builder.Default
    private Integer participantLimit = 0;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
    @Builder.Default
    private Boolean requestModeration = true;
    @JsonProperty(value = "state")
    private EventState eventState;
    @Size(min = 3, max = 120)
    private String title;
    private Integer views;
}
