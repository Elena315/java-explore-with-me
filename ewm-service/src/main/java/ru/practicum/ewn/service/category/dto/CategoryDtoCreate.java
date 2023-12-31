package ru.practicum.ewn.service.category.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
public class CategoryDtoCreate {
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String name;
}
