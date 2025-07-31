package afpa.fr.cballot.mappers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.ElectionDto;
import afpa.fr.cballot.dtos.PairDto;
import afpa.fr.cballot.entities.Pair;

@Service
public class PairDtoMapper implements Function<Pair, PairDto> {

    @Autowired
    private ElectionDtoMapper electionDtoMapper;

    @Override
    public PairDto apply(Pair pair) {
        PairDto pairDto = new PairDto();
        pairDto.setId(pair.getId());
        pairDto.setCounter(pair.getCounter());
        ElectionDto electionDto = electionDtoMapper.apply(pair.getElection());
        pairDto.setElection(electionDto);

        return pairDto;
    }

}
