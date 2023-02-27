package ch.zkmf2024.server.dto;

public record VereinsinfoDTO(
        Long logoImgId,
        Long bildImgId
) implements IsValid {
    @Override
    public boolean isValid() {
        // input not necessarily needed
        return true;
    }
}
