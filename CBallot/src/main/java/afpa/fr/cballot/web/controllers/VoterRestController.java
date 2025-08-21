package afpa.fr.cballot.web.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import afpa.fr.cballot.dtos.PairDto;
import afpa.fr.cballot.dtos.VoterDto;
import afpa.fr.cballot.entities.Pair;
import afpa.fr.cballot.entities.Voter;
import afpa.fr.cballot.mappers.PairDtoMapper;
import afpa.fr.cballot.mappers.PairMapper;
import afpa.fr.cballot.mappers.VoterDtoMapper;
import afpa.fr.cballot.mappers.VoterMapper;
import afpa.fr.cballot.repositories.PairRepository;
import afpa.fr.cballot.repositories.VoterRepository;
import afpa.fr.cballot.services.PairService;
import afpa.fr.cballot.services.VoterService;

@RestController
@RequestMapping("/api/voter")
public class VoterRestController {

    @Autowired
    private PairService pairService;
    @Autowired
    private VoterService voterService;
    @Autowired
    private PairRepository pairRepository;
    @Autowired
    private PairMapper pairMapper;
    @Autowired
    private VoterMapper voterMapper;
    @Autowired
    private PairDtoMapper pairDtoMapper;
    @Autowired
    private VoterDtoMapper voterDtoMapper;
    @Autowired
    private VoterRepository voterRepository;

    /**
     * Renvoyer la liste de tous les votants.
     * 
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VoterDto> getAllVoters() {
        return voterService.findAll();
    }

    /**
     * Créer un votant.
     * 
     * @param id
     * @param idPair
     * @return
     */
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public VoterDto createVoter(@RequestBody VoterDto voterDto) {
        Voter voter = voterMapper.apply(voterDto);
        voter = voterService.save(voter);
        return voterDtoMapper.apply(voter);
    }

    /**
     * Renvoie le résultat du vote d'un votant.
     */
    @PostMapping(value = "/{id}/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public PairDto submitVote(@PathVariable UUID id, @RequestBody Integer idPair) {
        // Récupère le votant
        Voter voter = voterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Votant introuvable."));

        if (voter.getVote_cast() != null && voter.getVote_cast()) {
            throw new RuntimeException("Vous avez déjà voté.");
        }

        // Vérifie que le binôme existe
        PairDto pair = pairService.findById(idPair)
                .orElseThrow(() -> new RuntimeException("Le binôme n'existe pas."));

        // Vérifie que le binôme correcpond à l'élection
        if (!pair.getElection().getId().equals(voter.getElection().getId())) {
            throw new RuntimeException("Le binôme n'appartient pas à cette élection.");
        }

        voter.setVote_cast(true);
        voterRepository.save(voter);

        // Incrémente le counter du binôme
        pair.setCounter(pair.getCounter() + 1);
        Pair updatedPair = pairRepository.save(pairMapper.apply(pair));

        return pairDtoMapper.apply(updatedPair);
    }
}
