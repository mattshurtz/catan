package shared.exceptions;

/**
 * This exception is thrown whenever a request from the Server Proxy to the server fails in an unexpected way (connection issues, timeout, etc.)
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class HTTPBadRequest extends Exception {
    
    public HTTPBadRequest() {
        super();
    }
    
    public HTTPBadRequest(String message) {
        super( message );
    }
    
    public HTTPBadRequest( Exception cause ){
        super( cause );
    }
    
}
