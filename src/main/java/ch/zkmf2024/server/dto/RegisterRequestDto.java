package ch.zkmf2024.server.dto;


public record RegisterRequestDto(String email, String password, String name, String contactFirstName, String contactLastName) {
}
