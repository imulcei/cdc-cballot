package afpa.fr.cballot.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import afpa.fr.cballot.dtos.ElectionDto;
import afpa.fr.cballot.dtos.PairDto;
import afpa.fr.cballot.entities.Election;
import afpa.fr.cballot.entities.Pair;
import afpa.fr.cballot.mappers.ElectionDtoMapper;
import afpa.fr.cballot.mappers.ElectionMapper;
import afpa.fr.cballot.mappers.PairDtoMapper;
import afpa.fr.cballot.mappers.PairMapper;
import afpa.fr.cballot.repositories.PairRepository;
import afpa.fr.cballot.services.ElectionService;
import afpa.fr.cballot.services.PairService;

@RestController
@RequestMapping("/api/election")
public class ElectionRestController {

    @Autowired
    private PairService pairService;
    @Autowired
    private PairDtoMapper pairDtoMapper;
    @Autowired
    private PairMapper pairMapper;
    @Autowired
    private PairRepository pairRepository;

    @Autowired
    private ElectionService electionService;
    @Autowired
    private ElectionMapper electionMapper;
    @Autowired
    private ElectionDtoMapper electionDtoMapper;

    /**
     * Renvoyer la liste de tous les binomes.
     * 
     * @return
     */
    @GetMapping("/pairs")
    @ResponseStatus(HttpStatus.OK)
    public List<PairDto> getAllPairs() {
        return pairService.findAll();
    }

    /**
     * Créer une élection.
     * 
     * @param electionDto
     * @return
     */
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ElectionDto createElection(@RequestBody ElectionDto electionDto) {
        Election election = electionMapper.apply(electionDto);
        election = electionService.createElection(election);
        return electionDtoMapper.apply(election);
    }

    /**
     * Créer un binôme.
     * 
     */
    @PostMapping(value = "/create-pair")
    @ResponseStatus(HttpStatus.CREATED)
    public PairDto createPair(@RequestBody PairDto pairDto) {
        Pair pair = pairMapper.apply(pairDto);
        pair = pairRepository.save(pair);
        pairDto.setId(pair.getId());
        return pairDto;
    }

    /**
     * Modifier un binome en modifiant le numéro de son binome.
     * 
     * @param id      L'identifiant du binome à modifier.
     * @param pairDto Binome à modifier.
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<PairDto> updatePair(@PathVariable Integer id, @RequestBody PairDto pairDto) {
        return pairService.update(id, pairDto)
                .map(updatedPair -> ResponseEntity.ok(pairDtoMapper.apply(updatedPair)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprimer un binôme.
     * 
     * @param id identifiant du binome.
     * @return
     */
    @DeleteMapping("pair/{id}")
    public ResponseEntity<Void> removePair(@PathVariable Integer id) {
        if (pairService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cloturer une élection.
     */
    @PostMapping("/election/close/{id}")
    public ResponseEntity<Void> closeElection(@PathVariable Integer id) {
        electionService.closeElection(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Supprimer une élection.
     * 
     * @param id identifiant de l'élection.
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeElection(@PathVariable Integer id) {
        if (electionService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
