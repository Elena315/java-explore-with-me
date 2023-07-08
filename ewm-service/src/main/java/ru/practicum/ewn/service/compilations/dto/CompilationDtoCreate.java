package ru.practicum.ewn.service.compilations.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
public class CompilationDtoCreate {
    @NotNull
    private List<Long> events;
    @NotNull
    private Boolean pinned;
    @NotNull
    @Size(max = 50)
    private String title;
}