package afpa.fr.cballot.dtos;

public class ErrorDTO {
    private String message;
    private boolean error = true;

    public ErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }

}
