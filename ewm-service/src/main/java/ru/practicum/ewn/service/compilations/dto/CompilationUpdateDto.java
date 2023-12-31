package ru.practicum.ewn.service.compilations.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
public class CompilationUpdateDto {
    private List<Long> events;
    @Builder.Default
    private Boolean pinned = false;
    @Size(max = 50)
    private String title;
}
