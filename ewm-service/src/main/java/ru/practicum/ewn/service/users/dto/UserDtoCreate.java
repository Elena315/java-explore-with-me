package ru.practicum.ewn.service.users.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Builder
@Jacksonized
public class UserDtoCreate {
    @NotBlank
    @Size(min = 2, max = 250)
    String name;
    @Email
    @NotBlank
    @Size(min = 6, max = 254)
    String email;
}
