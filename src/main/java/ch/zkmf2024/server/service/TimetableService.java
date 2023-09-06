package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.VereinSelectionDTO;
import ch.zkmf2024.server.dto.admin.LocationSelectionDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryCreateDTO;
import ch.zkmf2024.server.dto.admin.TimetableEntryDTO;
import ch.zkmf2024.server.dto.admin.VereinProgrammSelectionDTO;
import ch.zkmf2024.server.jooq.generated.tables.pojos.JudgeReportPojo;
import ch.zkmf2024.server.jooq.generated.tables.pojos.TimetableEntryPojo;
import ch.zkmf2024.server.repository.JudgeRepository;
import ch.zkmf2024.server.repository.LocationRepository;
import ch.zkmf2024.server.repository.TimetableRepository;
import ch.zkmf2024.server.repository.VereinRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static ch.zkmf2024.server.dto.JudgeReportStatus.NEW;
import static ch.zkmf2024.server.dto.LocationType.WETTSPIELLOKAL;

@Slf4j
@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final LocationRepository locationRepository;
    private final VereinRepository vereinRepository;
    private final JudgeRepository judgeRepository;

    public TimetableService(TimetableRepository timetableRepository,
                            LocationRepository locationRepository,
                            VereinRepository vereinRepository,
                            JudgeRepository judgeRepository) {
        this.timetableRepository = timetableRepository;
        this.locationRepository = locationRepository;
        this.vereinRepository = vereinRepository;
        this.judgeRepository = judgeRepository;
    }

    public List<TimetableEntryDTO> findAll() {
        return timetableRepository.findAll();
    }

    public List<LocationSelectionDTO> findWettspiellokale() {
        return locationRepository.findAllSelectionByType(WETTSPIELLOKAL);
    }

    public List<VereinSelectionDTO> findVereine() {
        return vereinRepository.findAllForSelection();
    }

    public List<VereinProgrammSelectionDTO> findProgrammeByVerein(Long vereinId) {
        return vereinRepository.findProgrammeSelection(vereinId);
    }

    public void create(TimetableEntryCreateDTO dto) {
        var pojo = new TimetableEntryPojo(
                null,
                dto.vereinId(),
                dto.vereinProgrammId(),
                dto.locationId(),
                dto.date(),
                dto.start(),
                dto.end()
        );
        timetableRepository.insert(pojo);

        judgeRepository.insert(new JudgeReportPojo(null, dto.judge1Id(), pojo.getId(), null, NEW.name(), null));
        judgeRepository.insert(new JudgeReportPojo(null, dto.judge2Id(), pojo.getId(), null, NEW.name(), null));
        judgeRepository.insert(new JudgeReportPojo(null, dto.judge3Id(), pojo.getId(), null, NEW.name(), null));
    }
}
