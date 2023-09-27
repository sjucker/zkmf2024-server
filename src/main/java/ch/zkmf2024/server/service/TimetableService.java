package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.VereinSelectionDTO;
import ch.zkmf2024.server.dto.admin.LocationSelectionDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryCreateDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryDTO;
import ch.zkmf2024.server.dto.admin.VereinProgrammSelectionDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static ch.zkmf2024.server.dto.LocationType.WETTSPIELLOKAL;

@Slf4j
@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final LocationRepository locationRepository;
    private final VereinRepository vereinRepository;

    public TimetableService(TimetableRepository timetableRepository,
                            LocationRepository locationRepository,
                            VereinRepository vereinRepository) {
        this.timetableRepository = timetableRepository;
        this.locationRepository = locationRepository;
        this.vereinRepository = vereinRepository;
    }

    public List<TimetableEntryDTO> findAll() {
        return timetableRepository.findAll();
    }

    public List<LocationSelectionDTO> findWettspiellokale() {
        return locationRepository.findAllSelectionByType(WETTSPIELLOKAL);
    }

    public List<VereinSelectionDTO> findVereine() {
        return vereinRepository.findAllNotYetPlanned();
    }

    public List<VereinProgrammSelectionDTO> findProgrammeByVerein(Long vereinId) {
        return vereinRepository.findProgrammeSelection(vereinId);
    }

    public void create(TimetableEntryCreateDTO dto) {
        timetableRepository.insert(new TimetableEntryPojo(
                null,
                dto.vereinId(),
                dto.vereinProgrammId(),
                dto.locationId(),
                dto.date(),
                dto.start(),
                dto.end()
        ));
    }
}
