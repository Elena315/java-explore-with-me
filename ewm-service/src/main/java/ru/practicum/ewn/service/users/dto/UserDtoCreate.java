package ru.practicum.ewn.service.users.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
public class UserDtoCreate {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    String name;
    @Email(message = "Некорректно заполнен email пользователя")
    @NotBlank(message = "Некорректно заполнен email пользователя")
    String email;
}
