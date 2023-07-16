package ru.practicum.ewn.service.users.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Value
@Builder
@Jacksonized
public class UserDtoResponse {
    Long id;
    @Size(max = 63)
    String name;
    @Email
    @Size(max = 64)
    String email;
}
