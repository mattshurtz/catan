/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands;

import server.data.PlayerInfoCookie;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.Credentials;
import shared.communication.responses.GameResponse;
import shared.communication.responses.PlayerResponse;
import shared.exceptions.HTTPBadRequest;
import shared.json.Deserializer;
import shared.json.Serializer;
import shared.model.Model;

/**
 *
 */
public abstract class Command {
    
    private Deserializer deserializer;
    private Serializer serializer;
    
    /**
     * Calls the function in the model to perform the given task in the 
     * classes that extend the Command class. Here they call the corresponding method
     * in the model to first check if the requested command is valid, and then perform
     * the command is it is valid. 
     * @param json 
     * @throws HTTPBadRequest 
     */
    public abstract String execute(String json, int gameID, int playerId) throws HTTPBadRequest;
    
    public Command()
    {
        deserializer = new Deserializer();
        serializer = new Serializer();
    }
    
    public boolean isUserInGame(int gameID, int userID){
        GameResponse game = GameInfoContainer.getInstance().getListOfGames().get(gameID);
        for(PlayerResponse player: game.getPlayers()){
            if(player.getId()== userID){
                return true;
            }
        }
        return false;
    }
    
    public Deserializer getDeserializer() {
        if(deserializer==null) {
        	deserializer = new Deserializer();
        }    	
    	return deserializer;
    }

    public void setDeserializer(Deserializer deserializer) {
        this.deserializer = deserializer;
    }

    public Serializer getSerializer() {
        if(serializer == null) {
            
        }
        return serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }
    
    public String buildUserCookie(Credentials creds, int userId) {
    	PlayerInfoCookie pinf = new PlayerInfoCookie();
        
        pinf.setName(creds.getUsername());
        pinf.setPassword( creds.getPassword());
        pinf.setId(userId);
        
        String ret = serializer.toJson( pinf );
        ret = serializer.encodeURIComponent( ret );
        
        String cookie = "catan.user=" + ret + ";Path=/;";
        return cookie;
    }
    
    public String buildGameCookie(int gameID) {
    	return "catan.game=" + gameID + ";Path=/;";
    }
    
    protected void addHistoryMessage( int gameId, String msg, int playerId ) {
        Model currentModel = GameInfoContainer.getInstance().getGameModel(gameId);
        
        // Add the user's name on the front of msg
        String playerName = GameInfoContainer.getInstance().getGameModel(gameId).getPlayerNameById( playerId );
        
        currentModel.addHistoryMessage( playerId, playerName + " " + msg );
    }
    
}
