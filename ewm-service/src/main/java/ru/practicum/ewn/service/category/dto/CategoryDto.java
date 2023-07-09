package ru.practicum.ewn.service.category.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Jacksonized
public class CategoryDto {
    private Integer id;
    @NotBlank
    @Size(max = 50)
    private String name;
}
