package afpa.fr.cballot.mappers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.PairDto;
import afpa.fr.cballot.entities.Pair;
import afpa.fr.cballot.services.ElectionService;

@Service
public class PairDtoMapper implements Function<Pair, PairDto> {

    @Autowired
    private ElectionService electionService;
    @Autowired
    private ElectionDtoMapper electionDtoMapper;

    @Override
    public PairDto apply(Pair pair) {
        PairDto pairDto = new PairDto();
        pairDto.setId(pair.getId());
        pairDto.setCounter(pair.getCounter());
        pairDto.setElection(electionDtoMapper.apply(electionService.findById_Election(pair.getElection().getId())));

        return pairDto;
    }

}
