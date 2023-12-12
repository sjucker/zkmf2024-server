package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.SponsorDTO;
import ch.zkmf2024.server.dto.SponsorType;
import ch.zkmf2024.server.dto.SponsoringDTO;
import ch.zkmf2024.server.repository.SponsoringRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static ch.zkmf2024.server.dto.SponsorType.DELUXE_PARTNER;
import static ch.zkmf2024.server.dto.SponsorType.GOENNER;
import static ch.zkmf2024.server.dto.SponsorType.HAUPTSPONSOR;
import static ch.zkmf2024.server.dto.SponsorType.MUSIKFAN;
import static ch.zkmf2024.server.dto.SponsorType.PREMIUM_PARTNER;
import static ch.zkmf2024.server.dto.SponsorType.SPONSOR;

@Slf4j
@Service
public class SponsoringService {

    private final SponsoringRepository sponsoringRepository;

    public SponsoringService(SponsoringRepository sponsoringRepository) {
        this.sponsoringRepository = sponsoringRepository;
    }

    public SponsoringDTO getSponsoring() {
        var allPerType = sponsoringRepository.findAllPerType();

        return new SponsoringDTO(
                shuffle(allPerType, HAUPTSPONSOR),
                shuffle(allPerType, PREMIUM_PARTNER),
                shuffle(allPerType, DELUXE_PARTNER),
                shuffle(allPerType, SPONSOR, 30),
                shuffle(allPerType, MUSIKFAN, 50),
                shuffle(allPerType, GOENNER)
        );
    }

    private static List<SponsorDTO> shuffle(Map<SponsorType, List<SponsorDTO>> allPerType, SponsorType sponsorType) {
        return shuffle(allPerType.getOrDefault(sponsorType, List.of()), null);
    }

    private static List<SponsorDTO> shuffle(Map<SponsorType, List<SponsorDTO>> allPerType, SponsorType sponsorType, Integer limit) {
        return shuffle(allPerType.getOrDefault(sponsorType, List.of()), limit);
    }

    private static List<SponsorDTO> shuffle(List<SponsorDTO> list, Integer limit) {
        Collections.shuffle(list);
        if (limit != null) {
            return list.stream()
                       .limit(limit)
                       .toList();
        } else {
            return list;
        }
    }
}
