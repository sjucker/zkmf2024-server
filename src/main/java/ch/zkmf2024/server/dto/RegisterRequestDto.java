package ch.zkmf2024.server.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private final String email;
    private final String name;
    private final String contactFirstName;
    private final String contactLastName;
}
