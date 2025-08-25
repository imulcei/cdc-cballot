package afpa.fr.cballot.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.PairDto;
import afpa.fr.cballot.entities.Pair;

@Service
public class PairDtoMapper implements Function<Pair, PairDto> {

    @Override
    public PairDto apply(Pair pair) {
        PairDto pairDto = new PairDto();
        pairDto.setId(pair.getId());
        pairDto.setCounter(pair.getCounter());
        pairDto.setId_election(pair.getElection().getId());
        pairDto.setStudents(pair.getStudents());

        return pairDto;
    }

}
