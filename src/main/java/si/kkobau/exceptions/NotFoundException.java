package si.kkobau.exceptions;

public class NotFoundException extends RuntimeException {

    private final String notFoundMessage;

    public NotFoundException(String notFoundMessage) {
        super(notFoundMessage);
        this.notFoundMessage = notFoundMessage;
    }

    public String getNotFoundMessage() {
        return notFoundMessage;
    }
}
