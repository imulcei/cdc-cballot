package afpa.fr.cballot.mappers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.ElectionDto;
import afpa.fr.cballot.entities.Election;
import afpa.fr.cballot.entities.Voter;
import afpa.fr.cballot.services.ElectionService;

@Service
public class ElectionMapper implements Function<ElectionDto, Election> {

    @Autowired
    private VoterMapper voterMapper;
    @Autowired
    private ElectionService electionService;

    @Override
    public Election apply(ElectionDto electionDto) {
        Election election = new Election();
        election.setId(electionDto.getId());
        election.setStart_date(electionDto.getStart_date());
        election.setEnd_date(electionDto.getEnd_date());
        election.setSession(electionService.findById(electionDto.getId_session()));
        election.setPairs(electionDto.getPairs());

        List<Voter> voters = electionDto.getVoters()
                .stream()
                .map(voterMapper)
                .collect(Collectors.toList());
        election.setVoters(voters);

        return election;
    }

}
