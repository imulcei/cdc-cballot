package afpa.fr.cballot.dtos;

public class PairDto {
    private Integer id;
    private Integer counter;
    private ElectionDto election;

    public PairDto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public ElectionDto getElection() {
        return election;
    }

    public void setElection(ElectionDto election) {
        this.election = election;
    }

}
