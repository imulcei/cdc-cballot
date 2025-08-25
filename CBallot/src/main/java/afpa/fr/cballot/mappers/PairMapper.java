package afpa.fr.cballot.mappers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.PairDto;
import afpa.fr.cballot.entities.Pair;
import afpa.fr.cballot.services.ElectionService;

@Service
public class PairMapper implements Function<PairDto, Pair> {

    @Autowired
    private ElectionService electionService;

    @Override
    public Pair apply(PairDto pairDto) {
        Pair pair = new Pair();
        pair.setId(pairDto.getId());
        pair.setCounter(pairDto.getCounter());
        pair.setElection(electionService.findById_Election(pairDto.getId_election()));

        return pair;
    }
}
