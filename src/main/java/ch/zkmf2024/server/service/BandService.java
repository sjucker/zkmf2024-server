package ch.zkmf2024.server.service;

import ch.zkmf2024.server.dto.BandDTO;
import ch.zkmf2024.server.mapper.DTOMapper;
import ch.zkmf2024.server.repository.BandRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BandService {
    private final BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public Optional<BandDTO> find(String email) {
        return bandRepository.findByEmail(email)
                             .map(DTOMapper.INSTANCE::toDTO);

    }

}
