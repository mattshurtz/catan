package shared.communication.params.moves;

/**
 * A class representing a basic move request.
 * This class is equivalent to a finishTurn request,
 * buyDevCard request, and playMonumentCard request
 * (all other move requests extend this class because
 *  they have 
 */
public class MoveRequest {
    
    /**
     * The type of move
     */
    private String type = null;
    
    /**
     * the index of the player making the move
     */
    int playerIndex = -1;
    
    public MoveRequest(String type, int playerIndex) {
    	this.type = type;
    	this.playerIndex = playerIndex;
    }
    
    public MoveRequest(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
}
