package afpa.fr.cballot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.VoterDto;
import afpa.fr.cballot.entities.Voter;
import afpa.fr.cballot.mappers.VoterDtoMapper;
import afpa.fr.cballot.repositories.VoterRepository;

@Service
public class VoterService {
    private VoterRepository voterRepository;
    private VoterDtoMapper voteDtoMapper;

    public VoterService(VoterRepository voterRepository, VoterDtoMapper voteDtoMapper) {
        this.voterRepository = voterRepository;
        this.voteDtoMapper = voteDtoMapper;
    }

    public List<VoterDto> findAll() {
        return voterRepository.findAll().stream().map(voteDtoMapper::apply).collect(Collectors.toList());
    }

    public Voter save(Voter voter) {
        return voterRepository.save(voter);
    }
}
