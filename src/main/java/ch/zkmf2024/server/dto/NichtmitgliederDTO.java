package ch.zkmf2024.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static ch.zkmf2024.server.util.ValidationUtil.isPositive;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public record NichtmitgliederDTO(Integer amount,
                                 String instrument) {

    @JsonIgnore
    public boolean isNotEmpty() {
        return isPositive(amount) && isNotBlank(instrument);
    }
}
