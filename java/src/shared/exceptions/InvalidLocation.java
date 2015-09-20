package shared.exceptions;

/**
 *This exception is thrown when a player tries to place a piece on the map in an invalid location. 
 */
public class InvalidLocation extends Exception {

    /**
     * Creates a new instance of <code>InvalidLocation</code> without detail
     * message.
     */
    public InvalidLocation() {
    }

    /**
     * Constructs an instance of <code>InvalidLocation</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public InvalidLocation(String msg) {
        super(msg);
    }
}
