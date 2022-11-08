package ch.zkmf2024.server.dto;

public record KontaktDTO(String vorname,
                         String nachname,
                         String adresse,
                         Integer plz,
                         String ort,
                         String email,
                         String telefonPrivat,
                         String telefonMobile) {
}
