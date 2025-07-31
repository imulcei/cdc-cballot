package afpa.fr.cballot.mappers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.ElectionDto;
import afpa.fr.cballot.dtos.VoterDto;
import afpa.fr.cballot.entities.Election;

@Service
public class ElectionDtoMapper implements Function<Election, ElectionDto> {

    @Autowired
    private VoterDtoMapper voteDtoMapper;

    @Override
    public ElectionDto apply(Election election) {
        ElectionDto electionDto = new ElectionDto();
        electionDto.setId(election.getId());
        electionDto.setStart_date(election.getStart_date());
        electionDto.setEnd_date(election.getEnd_date());
        electionDto.setPairs(election.getPairs());

        List<VoterDto> voteDtos = election.getVoters()
                .stream()
                .map(voteDtoMapper)
                .collect(Collectors.toList());
        electionDto.setVoters(voteDtos);

        return electionDto;
    }

}
