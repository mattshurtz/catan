package shared.communication.params;

/**
 * Wrapper object for the IServerProxy.joinGame request
 *
 * @author Alex&Nicky
 *
 * JoinGameRequest { id (integer): The ID of the game to join, color (string) =
 * ['red' or 'green' or 'blue' or 'yellow' or 'puce' or 'brown' or 'white' or
 * 'purple' or 'orange']: What color you want to join (or rejoin) as. }
 */
public class JoinGameRequest {
	//int id - ID of the game the player wants to join
    //String color - color that the player wants to be

    int gameID;
    String color;

    public JoinGameRequest(int gameID, String color) {
        this.gameID = gameID;
        this.color = color;
    }

}
