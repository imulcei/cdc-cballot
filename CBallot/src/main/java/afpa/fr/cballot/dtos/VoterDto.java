package afpa.fr.cballot.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class VoterDto {
    private UUID id;
    private Boolean vote_cast;
    private String email;
    private Integer electionId;
    @JsonIgnore
    private ElectionDto election;

    public VoterDto() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getVote_cast() {
        return vote_cast;
    }

    public void setVote_cast(Boolean vote_cast) {
        this.vote_cast = vote_cast;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ElectionDto getElection() {
        return election;
    }

    public void setElection(ElectionDto election) {
        this.election = election;
    }

    public Integer getElectionId() {
        electionId = election.getId();
        return electionId;
    }

    public void setElectionId(Integer electionId) {
        this.electionId = electionId;
    }
}
