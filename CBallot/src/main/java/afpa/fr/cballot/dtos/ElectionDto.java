package afpa.fr.cballot.dtos;

import java.sql.Timestamp;
import java.util.List;

import afpa.fr.cballot.entities.Pair;

public class ElectionDto {
    private Integer id;
    private Timestamp start_date;
    private Timestamp end_date;
    private List<Pair> pairs;
    private List<VoterDto> voters;
    // private SessionDto session;

    public ElectionDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public List<VoterDto> getVoters() {
        return voters;
    }

    public void setVoters(List<VoterDto> voters) {
        this.voters = voters;
    }

}
