package ru.practicum.ewn.service.users.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class UserShortDto {
    Long id;
    @NotBlank(message = "Имя пользователя не может быть пустым")
    String name;
}
