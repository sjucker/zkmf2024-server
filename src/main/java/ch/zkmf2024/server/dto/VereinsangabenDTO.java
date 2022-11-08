package ch.zkmf2024.server.dto;

public record VereinsangabenDTO(String vereinsname,
                                String adresse,
                                Integer plz,
                                String ort,
                                String homepage,
                                String iban) {
}
