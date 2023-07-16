package ru.practicum.ewn.service.users.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;

@Value
@Builder
@Jacksonized
public class UserDtoCreate {
    @NotNull
    @NotBlank
    @Size(min = 2, max = 250)
    String name;
    @Email
    @NotNull
    @NotBlank
    @Size(min = 6, max = 254)
    @Pattern(regexp = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+$")
    String email;
}
