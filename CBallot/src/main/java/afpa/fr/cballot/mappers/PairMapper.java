package afpa.fr.cballot.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.PairDto;
import afpa.fr.cballot.entities.Pair;

@Service
public class PairMapper implements Function<PairDto, Pair> {

    @Override
    public Pair apply(PairDto pairDto) {
        Pair pair = new Pair();
        pair.setId(pair.getId());
        pair.setCounter(pair.getCounter());
        pair.setElection(pair.getElection());

        return pair;
    }
}
