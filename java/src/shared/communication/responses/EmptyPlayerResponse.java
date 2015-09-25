package shared.communication.responses;

/**
 * Empty object representing a player.  A list of this object comes back when a new game is created (IServerProxy.createGame()) 
 *
 */
public class EmptyPlayerResponse {
    
    /**
     * Needed for the test functions
     * @param o
     * @return 
     */
    public boolean equals( Object o ) {
        if ( o instanceof EmptyPlayerResponse )
            return true;
        else 
            return false;
    }
}
