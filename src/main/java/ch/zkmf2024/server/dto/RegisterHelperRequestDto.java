package ch.zkmf2024.server.dto;

import java.time.LocalDate;
import java.util.List;

public record RegisterHelperRequestDto(String name, String email, String comment, List<LocalDate> checkedDays) {

}
