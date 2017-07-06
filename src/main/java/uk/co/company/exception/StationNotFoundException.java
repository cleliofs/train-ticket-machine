package uk.co.company.exception;

/**
 * A business runtime exception to inform when a train station is not found.
 *
 * In a real Web app we should map this exception to a 404 HTTP status
 * code on the exception global handling mechanism (Spring MVC)
 *
 * Created by clelio on 21/04/15.
 */
public class StationNotFoundException extends RuntimeException {

    public StationNotFoundException() {
        super();
    }

    public StationNotFoundException(String message) {
        super(message);
    }

    public StationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
