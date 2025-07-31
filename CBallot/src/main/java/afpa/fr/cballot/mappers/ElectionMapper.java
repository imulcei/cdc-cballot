package afpa.fr.cballot.mappers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.ElectionDto;
import afpa.fr.cballot.entities.Election;
import afpa.fr.cballot.entities.Voter;

@Service
public class ElectionMapper implements Function<ElectionDto, Election> {

    @Autowired
    private VoterMapper voteMapper;

    @Override
    public Election apply(ElectionDto electionDto) {
        Election election = new Election();
        election.setId(electionDto.getId());
        election.setStart_date(electionDto.getStart_date());
        election.setEnd_date(electionDto.getEnd_date());
        election.setPairs(electionDto.getPairs());

        List<Voter> voters = electionDto.getVoters()
                .stream()
                .map(voteMapper)
                .collect(Collectors.toList());
        election.setVoters(voters);

        return election;
    }

}
