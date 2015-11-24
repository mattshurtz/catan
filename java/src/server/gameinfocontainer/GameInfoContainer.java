package server.gameinfocontainer;

import java.util.List;

import shared.communication.responses.GameResponse;
import shared.definitions.CatanColor;
import shared.exceptions.GetPlayerException;
import shared.model.Model;
import shared.model.Player;

/**
 *
 * @author Scott
 */
public class GameInfoContainer {
	
	private static GameInfoContainer instance;
	
	public static GameInfoContainer getInstance() {
		if (instance == null) {
			instance = new GameInfoContainer();
		}
		return instance;
	}
	
	public static void reset() {
		instance = null;
	}
	
    private ModelBank models;
    private UserInfoBank users;
    
    /**
     * Constructs the GameInfoContainer Object
     */
    GameInfoContainer() {
        models = new ModelBank();
        users = new UserInfoBank();
    }
    
    GameInfoContainer(ModelBank games, UserInfoBank users) {
        models = games;
        users = users;
    }
    
    
    /**
     * Called by the create.java command class and adds a game to the 
     * GameInfoContainer
     * @param randomTiles 
     * @param randomPorts 
     * @param randomNumbers 
     * @param name 
     * @return 
     */
    public int createGame(String name, boolean randomNumbers, boolean randomPorts, boolean randomTiles){
        int gameid = this.getModels().addGame(new Model(name, randomNumbers, randomPorts, randomTiles));
        //return the id of the game just added
        return gameid;
    }
    
    /**
    *Checks password if it is valid adds user to 
     */
    public int login(String username, String password){
        return users.login(username, password);
    }
    
    /**
     * Adds player to the list of games stored in the correct model
     * @param i 
     * @param string 
     * @param playerId 
     */
    public boolean joinGame(int playerId, String color, int gameId){
        Model gameModel = models.getGame(gameId);
        if (gameModel == null)
        	return false;
        
        List<Player> players = gameModel.getPlayers();
        
        //check if player already exists
        	//if does update color
        boolean changedColor = false;
            for(Player player: gameModel.getPlayers()){
                if(player.getPlayerID() == playerId){
                	
                	//check for duplicate color
                	for(Player player2: gameModel.getPlayers()) {
                		if(player2.getPlayerID() != playerId && player2.getColor()==CatanColor.fromString(color)) {
                			return false;
                		}
                	}
                	
                	//else change color
                    player.setColor(CatanColor.fromString(color));
                    changedColor = true;
                }
            }
            
		if(!changedColor){
			//else lets check if we can add player
			if(gameModel.getPlayers().size() >= 4)
				return false;
			for(Player player: gameModel.getPlayers()){
				if (player.getColor()==CatanColor.fromString(color)) {
					return false;
				}
			}
			
			
			String playerName = users.getPlayerName(playerId);
	        gameModel.addPlayer( color, playerId, playerName );
        }
        
        return true;
    }
    
    /**
     * Returns a list of the games in this model
     */
    public String Games(){
        return "list of games";
    }
    
    /**
     * adds new registered player to the userInfoBank
     * @param string2 
     * @param string 
     */
    public int register(String username, String password){
        return users.addUser(username, password);
    }
    
    /**
     * 
     * @return ModelBank of game models currently being handled by the server.
     */
    public ModelBank getModels() {
    	return models;
    }
    
    public Model getGameModel(int gameID)
    {
        return models.getGame(gameID);
    }
  
//    /**
//     * 
//     * @return UserInfoBank of registered user information.
//     */
//    public UserInfoBank getRegisteredUserInfo() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    /**
     * 
     * @return List of GameResponses containing game information
     */
    public List<GameResponse> getListOfGames() {
        return models.toGameResponseList();
    }
}
