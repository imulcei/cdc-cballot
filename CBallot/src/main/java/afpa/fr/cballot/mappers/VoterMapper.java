package afpa.fr.cballot.mappers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.VoterDto;
import afpa.fr.cballot.entities.Election;
import afpa.fr.cballot.entities.Voter;

@Service
public class VoterMapper implements Function<VoterDto, Voter> {

    @Autowired
    private ElectionMapper electionMapper;

    @Override
    public Voter apply(VoterDto voterDto) {
        Voter voter = new Voter();
        voter.setId(voterDto.getId());
        voter.setVote_cast(voterDto.getVote_cast());
        voter.setEmail(voterDto.getEmail());
        Election election = electionMapper.apply(voterDto.getElection());
        voter.setElection(election);

        return voter;
    }

}
