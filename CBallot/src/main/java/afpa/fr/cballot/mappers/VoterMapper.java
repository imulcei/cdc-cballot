package afpa.fr.cballot.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.VoterDto;
import afpa.fr.cballot.entities.Voter;

@Service
public class VoterMapper implements Function<VoterDto, Voter> {

    // @Autowired
    // private ElectionMapper electionMapper;

    @Override
    public Voter apply(VoterDto voterDto) {
        Voter voter = new Voter();
        voter.setId(voterDto.getId());
        voter.setVote_cast(voterDto.getVote_cast());
        voter.setEmail(voterDto.getEmail());

        return voter;
    }

}
