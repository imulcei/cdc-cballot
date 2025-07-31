package afpa.fr.cballot.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import afpa.fr.cballot.dtos.PairDto;
import afpa.fr.cballot.entities.Pair;
import afpa.fr.cballot.mappers.PairDtoMapper;
import afpa.fr.cballot.repositories.PairRepository;

@Service
public class PairService {
    private final PairRepository pairRepository;
    private final PairDtoMapper pairDtoMapper;

    public PairService(PairRepository pairRepository, PairDtoMapper pairDtoMapper) {
        this.pairRepository = pairRepository;
        this.pairDtoMapper = pairDtoMapper;
    }

    public List<PairDto> findAll() {
        return pairRepository.findAll().stream().map(pairDtoMapper::apply).collect(Collectors.toList());
    }

    public Optional<PairDto> findById(Integer id) {
        return pairRepository.findById(id).map(pairDtoMapper::apply);
    }

    public Pair save(Pair pair) {
        return pairRepository.save(pair);
    }

    public Optional<Pair> update(Integer id, PairDto pairDto) {
        return pairRepository.findById(id).map(existingPair -> {
            existingPair.setId(pairDto.getId());
            return pairRepository.save(existingPair);
        });
    }

    public void delete(Pair pair) {
        pairRepository.delete(pair);
    }

    public boolean deleteById(Integer id) {
        return pairRepository.findById(id).map(pair -> {
            pairRepository.delete(pair);
            return true;
        }).orElse(false);
    }
}
