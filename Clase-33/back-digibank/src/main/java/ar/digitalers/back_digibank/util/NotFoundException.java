package ar.digitalers.back_digibank.util;


public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(final String message) {
        super(message);
    }

}
