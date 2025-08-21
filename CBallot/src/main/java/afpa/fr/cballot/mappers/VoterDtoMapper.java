package afpa.fr.cballot.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.VoterDto;
import afpa.fr.cballot.entities.Voter;

@Service
public class VoterDtoMapper implements Function<Voter, VoterDto> {

    // @Autowired
    // private ElectionDtoMapper electionDtoMapper;

    @Override
    public VoterDto apply(Voter vote) {
        VoterDto voteDto = new VoterDto();
        voteDto.setId(vote.getId());
        voteDto.setVote_cast(vote.getVote_cast());
        voteDto.setEmail(vote.getEmail());

        voteDto.setElectionId(vote.getElection().getId());

        return voteDto;
    }

}
