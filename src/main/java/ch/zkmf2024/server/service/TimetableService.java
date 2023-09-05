package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.LocationType;
import ch.zkmf2024.server.dto.VereinSelectionDTO;
import ch.zkmf2024.server.dto.admin.LocationSelectionDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryDTO;
import ch.zkmf2024.server.dto.admin.VereinProgrammSelectionDTO;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return locationRepository.findAllSelectionByType(LocationType.WETTSPIELLOKAL);
    }

    public List<VereinSelectionDTO> findVereine() {
        return vereinRepository.findAllForSelection();
    }

    public List<VereinProgrammSelectionDTO> findProgrammeByVerein(Long vereinId) {
        return vereinRepository.findProgrammeSelection(vereinId);
    }
}
