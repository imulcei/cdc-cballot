package afpa.fr.cballot.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import afpa.fr.cballot.dtos.PairDto;
import afpa.fr.cballot.dtos.VoterDto;
import afpa.fr.cballot.entities.Voter;
import afpa.fr.cballot.mappers.VoterMapper;
import afpa.fr.cballot.repositories.VoterRepository;
import afpa.fr.cballot.services.PairService;

@RestController
@RequestMapping("/vote")
public class VoteRestController {

    @Autowired
    private PairService pairService;

    @Autowired
    private VoterMapper voterMapper;
    @Autowired
    private VoterRepository voterRepository;

    @Autowired

    /**
     * Renvoyer la liste de tous les binomes.
     * 
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PairDto> getAllPairs() {
        return pairService.findAll();
    }

    /**
     * Créer un vote pour une élection.
     * 
     * @param voterDto
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VoterDto createVote(@RequestBody VoterDto voterDto) {
        Voter voter = voterMapper.apply(voterDto);
        voter = voterRepository.save(voter);
        voterDto.setVote_cast(true);
        return voterDto;
    }

    /**
     * TODO: Envoyer un mail après la cloture du vote
     */
}
