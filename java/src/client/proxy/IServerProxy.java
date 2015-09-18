package client.proxy;
import java.util.*;

import shared.communication.params.*;
import shared.communication.responses.*;
import shared.communication.responses.model.*;
import shared.exceptions.ServerException;

/**
 * Interface for the Server Proxy and Mock Server Proxy.
 * Defines all functionality listed on the TA Swagger page
 * 
 * @author Alex Morris
 *
 */
public interface IServerProxy {
	// Operations about users
	/**
	 * Logs a user into the game system
	 * @param userCredentials The Credentials object containing the user's username and password
	 * @return True if the user successfully authenticates, false if not
	 * @throws ServerException If the server request fails
	 */
	boolean login(Credentials userCredentials) throws ServerException;
	
	/**
	 * Creates a user account
	 * @param userCredentials The Credentials object containing the new user's username and password
	 * @return True if the registry was successful, false if not
	 * @throws ServerException If the server request fails
	 */
	boolean register(Credentials userCredentials) throws ServerException;
	
	// Game queries/actions (pre-joining)
	/**
	 * Gets a list of the current games being played on the server
	 * @return A list of GameFromJSON objects, each containing the game's title, id, and a list of current players
	 * @throws ServerException If the server request fails
	 */
	List<GameResponse> getGamesList() throws ServerException;
	
	/**
	 * Creates a new game on the server
	 * @param gameRequests The CreateGameRequests object containing instructions on how to configure the new game
	 * @return NewGameResponse object containing the title and ID of the game, and a list of empty player objects
	 * @throws ServerException If the server requests fails
	 */
	NewGameResponse createGame(CreateGameRequest gameRequests) throws ServerException;
	
	/**
	 * Joins a player to a game on the server
	 * @param joinRequest JoinGameRequest object containing the id of the game the player wants to join, and the color the player wants
	 * @return True if the player successfully joined the game, false if not
	 * @throws ServerException If the server request fails
	 */
	boolean joinGame(JoinGameRequest joinRequest) throws ServerException;
	
	/**
	 * This is for testing and debugging purposes.  When a bug is found, a player will use this method to save the current state of the game to a file and attach the file to a bug report.
	 * @param saveRequest The SaveGameRequest object containing the ID of the game the player wants to save, and the name of the file they want to save to.
	 * @return True if the game was successfully saved, false if not
	 * @throws ServerException If the server request fails
	 */
	boolean saveGame(SaveGameRequest saveRequest) throws ServerException;
	
	/**
	 * This is for testing and debugging purposes.  When a bug is found, a player will save the game state to a file and attach that file to a bug report.
	 * This function can be used to load that saved game file.
	 * @param loadRequest the LoadGameRequest object containing the name of the save file
	 * @return True if the game was successfully loaded, false if not
	 * @throws ServerException If the server request fails
	 */
	boolean loadGame(LoadGameRequest loadRequest) throws ServerException;
	
	//Operations for the game you're in. (Requires cookie)
	/**
	 * Returns the entire model of the game.  This includes game board state, player state, resource allocation, etc.
	 * @param version The version number of the model that the caller already has.  You will only get a model back if the current model version on the server
	 * is newer than the version specified.  Otherwise, you'll get a string "true" to notify the caller that it already has the current model state
	 * @return TODO So this API request returns either a game model object in JSON, or true.  How should we deal with that?
	 * @throws ServerException If the server request fails
	 */
	ModelResponse getGameModel(int version) throws ServerException;
	
	/**
	 * For default games created by the server, this method reverts the game to the state immediately after the initial placement round.  For
	 * user-created games, this method reverts games to the very beginning (before the initial placement round).  The return happens after the game has
	 * been reset.  You must login and join a game before calling this method.
	 * @return The ModelResponse object containing the game model after the game has been reset.
	 * @throws ServerException If the server request fails
	 */
	ModelResponse resetGame() throws ServerException;
	
	//TODO: Continue stubbing TA swagger methods - start with /game/commands (GET)
	
        ModelResponse postCommands() throws ServerException;
        
        ModelResponse getCommands() throws ServerException;
        
        boolean addAi() throws ServerException;
        
        ModelResponse buildRoad() throws ServerException;   
        
        void offerTrade() throws ServerException;
    
        /**
        * type (acceptTrade),
        * playerIndex (integer): Who's accepting / rejecting this trade,
        * willAccept (boolean): Whether you accept the trade or not
        */
        void acceptTrade() throws ServerException;
    
        void maritimeTrade() throws ServerException;
    
        void buyDevCard() throws ServerException;
    
        void playYearOfPlenty() throws ServerException;
    
        void playRoadBuilding() throws ServerException;
    
        void playSoldier() throws ServerException;
    
        void playMonopoly() throws ServerException;
    
        void buildSettlement() throws ServerException;
    
        void buildCity() throws ServerException;
    
        void sendChat() throws ServerException;
    
        void rollNumber() throws ServerException;
    
        void robPlayer() throws ServerException;
    
        void finishTurn() throws ServerException;
	
	
	
	
	
}
